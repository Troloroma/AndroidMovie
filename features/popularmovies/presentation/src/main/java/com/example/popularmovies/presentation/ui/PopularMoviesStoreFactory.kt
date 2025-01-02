package com.example.popularmovies.presentation.ui

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.popularmovies.domain.PopularMoviesInteractor
import javax.inject.Inject
import javax.inject.Provider


class PopularMoviesStoreFactory(
    private val storeFactory: StoreFactory,
    private val interactor: PopularMoviesInteractor
) {

    fun create(): PopularMoviesStore =
        object : PopularMoviesStore,
            Store<PopularMoviesStore.Intent, PopularMoviesStore.State, Nothing> by storeFactory.create(
                name = "PopularMoviesStore",
                initialState = PopularMoviesStore.State(),
                bootstrapper = SimpleBootstrapper(PopularMoviesStore.Action.LoadMovies),
                executorFactory = { PopularMoviesExecutorImpl(interactor) },
                reducer = PopularMoviesReducerImpl
            ) {}
}
class PopularMoviesStoreFactoryProvider @Inject constructor(
    private val interactor: Provider<PopularMoviesInteractor>
) {
    fun create(): PopularMoviesStoreFactory {
        return PopularMoviesStoreFactory(DefaultStoreFactory(), interactor.get())
    }
}