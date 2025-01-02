package com.example.movie.presentation.ui

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.example.movie.domain.MovieEntity

sealed interface Msg {
    data class MovieLoaded(val movie: MovieEntity?) : Msg
    object LoadingStarted : Msg
    data class LoadingFailed(val error: String) : Msg
}

object MovieReducerImpl : Reducer<MovieStore.State, Msg> {
    override fun MovieStore.State.reduce(msg: Msg): MovieStore.State{
        Log.d("MovieReducerImpl", "Current state: $this, Message: $msg")
        return when (msg) {
            is Msg.LoadingStarted -> copy(isLoading = true, error = null)
            is Msg.MovieLoaded -> copy(movie = msg.movie, isLoading = false, error = null)
            is Msg.LoadingFailed -> copy(isLoading = false, error = msg.error)
        }
    }
}