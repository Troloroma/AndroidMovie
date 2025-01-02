package com.example.movie.presentation.ui

import com.arkivanov.mvikotlin.core.store.Store
import com.example.movie.domain.MovieEntity

interface MovieStore : Store<MovieStore.Intent, MovieStore.State, Nothing> {
    sealed interface Action {
        class Load(val movieId: Int) : Action
    }

    sealed interface Intent {
        class Reload(val movieId: Int) : Intent
    }

    data class State(
        val movie: MovieEntity? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )
}