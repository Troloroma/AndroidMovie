package com.example.popularmovies.presentation.ui.adapter

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.common.Entity
import com.example.popularmovies.domain.PopularMoviesInteractor
import com.example.popularmovies.presentation.ui.MovieModel

class MoviesPagingSource(
    private val interactor: PopularMoviesInteractor
) : PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val page = params.key ?: 1
        return try {
            when (val result = interactor.getPopularMovies(page)) {
                is Entity.Success -> {
                    val movies = result.data?.results?.mapNotNull { resultEntity ->
                        resultEntity?.let {
                            MovieModel(
                                id = it.id,
                                title = it.originalTitle,
                                posterPath = it.posterPath,
                                voteAverage = it.voteAverage,
                                overview = it.overview
                            )
                        }
                    }
                    LoadResult.Page(
                        data = movies.orEmpty(),
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (movies?.isEmpty() == true) null else page + 1
                    )
                }
                is Entity.Error -> TODO()
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}