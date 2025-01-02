package com.example.movie.presentation.ui

import android.util.Log
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.common.Entity
import com.example.movie.domain.MovieInteractor
import kotlinx.coroutines.launch

internal class MovieExecutorImpl(
    private val interactor: MovieInteractor
) : CoroutineExecutor<MovieStore.Intent, MovieStore.Action, MovieStore.State, Msg, Nothing>() {
    override fun executeIntent(intent: MovieStore.Intent) {
        when (intent) {
            is MovieStore.Intent.Reload -> loadData(movieId = intent.movieId)
        }
    }

    override fun executeAction(action: MovieStore.Action) {
        when (action) {
            is MovieStore.Action.Load -> loadData(movieId = action.movieId)
        }
    }

    private fun loadData(movieId: Int) {
        dispatch(Msg.LoadingStarted)
        scope.launch {
            when (val response = interactor.getMovie(movieId)) {
                is Entity.Success -> {
                    Log.d("MovieExecutorImpl", response.data.toString())
                    dispatch(Msg.MovieLoaded(response.data))
                }

                is Entity.Error -> {
                    dispatch(Msg.LoadingFailed(response.exceptionMessage))
                }
            }
        }
    }
}