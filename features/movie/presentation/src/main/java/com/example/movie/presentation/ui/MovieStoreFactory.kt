package com.example.movie.presentation.ui

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.movie.domain.MovieInteractor
import javax.inject.Inject
import javax.inject.Provider

class MovieStoreFactory(
    private val storeFactory: StoreFactory,
    private val interactor: MovieInteractor
) {

    fun create(movieId: Int): MovieStore =
        object : MovieStore,
            Store<MovieStore.Intent, MovieStore.State, Nothing> by storeFactory.create(
                name = "MovieStore",
                initialState = MovieStore.State(),
                bootstrapper = SimpleBootstrapper(MovieStore.Action.Load(movieId)),
                executorFactory = { MovieExecutorImpl(interactor) },
                reducer = MovieReducerImpl
            ) {}
}

class MovieStoreFactoryProvider @Inject constructor(
    private val interactor: Provider<MovieInteractor>
) {
    fun create(): MovieStoreFactory {
        return MovieStoreFactory(DefaultStoreFactory(), interactor.get())
    }
}