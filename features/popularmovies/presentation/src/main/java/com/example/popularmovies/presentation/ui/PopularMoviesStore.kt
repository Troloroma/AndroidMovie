package com.example.popularmovies.presentation.ui

import androidx.paging.PagingData
import com.arkivanov.mvikotlin.core.store.Store

interface PopularMoviesStore : Store<PopularMoviesStore.Intent, PopularMoviesStore.State, Nothing> {

    sealed interface Action {
        object LoadMovies : Action
    }

    sealed interface Intent {
        object ReloadMovies : Intent
    }

    data class State(
        val movies: PagingData<MovieModel> = PagingData.empty(),
        val isLoading: Boolean = false,
        val error: String? = null
    )
}