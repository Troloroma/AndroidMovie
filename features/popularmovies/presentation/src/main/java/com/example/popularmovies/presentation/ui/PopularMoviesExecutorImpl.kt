package com.example.popularmovies.presentation.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.popularmovies.domain.PopularMoviesInteractor
import com.example.popularmovies.presentation.ui.adapter.MoviesPagingSource
import kotlinx.coroutines.launch

internal class PopularMoviesExecutorImpl(
    private val interactor: PopularMoviesInteractor
) : CoroutineExecutor<PopularMoviesStore.Intent, PopularMoviesStore.Action, PopularMoviesStore.State, Msg, Nothing>() {

    override fun executeIntent(intent: PopularMoviesStore.Intent) {
        when(intent){
            is PopularMoviesStore.Intent.ReloadMovies -> loadMovies()
        }
    }

    override fun executeAction(action: PopularMoviesStore.Action) {
        when(action){
            is PopularMoviesStore.Action.LoadMovies -> loadMovies()
        }
    }

    private fun loadMovies() {
        dispatch(Msg.LoadingStarted)
        scope.launch {
            val pager = Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { MoviesPagingSource(interactor) }
            ).flow.cachedIn(scope)
            pager.collect { pagerData ->
                dispatch(Msg.MoviesLoaded(pagerData))
            }
        }
    }
}