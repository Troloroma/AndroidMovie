package com.example.popularmovies.presentation.ui

import android.util.Log
import androidx.paging.PagingData
import com.arkivanov.mvikotlin.core.store.Reducer

sealed interface Msg {
    data class MoviesLoaded(val movies: PagingData<MovieModel>) : Msg
    object LoadingStarted : Msg
    data class LoadingFailed(val error: String) : Msg
}

object PopularMoviesReducerImpl : Reducer<PopularMoviesStore.State, Msg> {
    override fun PopularMoviesStore.State.reduce(msg: Msg): PopularMoviesStore.State{
        Log.d("PopularMoviesReducerImpl", "Current state: $this, Message: $msg")
        return when (msg) {
            is Msg.LoadingStarted -> copy(isLoading = true, error = null)
            is Msg.MoviesLoaded -> copy(movies = msg.movies, isLoading = false)
            is Msg.LoadingFailed -> copy(isLoading = false, error = msg.error)
        }
    }
}