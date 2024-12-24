package com.example.popularmovies.presentation.di

import com.example.core.dagger.FeatureScoped
import com.example.core.dependency.Dependencies
import com.example.popularmovies.domain.PopularMoviesInteractor
import com.example.popularmovies.presentation.ui.PopularMoviesFragment
import dagger.Component

@FeatureScoped
@Component(dependencies = [PopularMoviesDeps::class])
interface PopularMoviesComponent {
    @Component.Factory
    interface Factory{
        fun create(deps: PopularMoviesDeps) : PopularMoviesComponent
    }
    fun injectFragment (popularMoviesFragment: PopularMoviesFragment)
}

interface PopularMoviesDeps : Dependencies {
    val popularMoviesInteractor: PopularMoviesInteractor
}
