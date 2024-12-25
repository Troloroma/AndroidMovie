package com.example.popularmovies.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.popularmovies.domain.PopularMoviesInteractor
import com.example.popularmovies.presentation.ui.adapter.MoviesPagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class PopularMoviesViewModel(
    private val interactor: PopularMoviesInteractor
) : ViewModel(){

    val popularMoviesList = MutableStateFlow<PagingData<MovieModel>>(PagingData.empty())

    init {
        getPopularMovies()
    }

    private fun getPopularMovies(){
        viewModelScope.launch {
            val pager = Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { MoviesPagingSource(interactor) }
            ).flow.cachedIn(viewModelScope)

            pager.collect { pagerData ->
                popularMoviesList.value = pagerData
            }
        }
    }

    companion object {
        class Factory @Inject constructor(
            private val interactor: Provider<PopularMoviesInteractor>
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == PopularMoviesViewModel::class.java)
                return PopularMoviesViewModel(interactor.get()) as T
            }
        }
    }
}