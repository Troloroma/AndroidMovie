package com.example.movie.presentation.di

import com.example.core.dagger.FeatureScoped
import com.example.core.dependency.Dependencies
import com.example.movie.domain.MovieInteractor
import com.example.movie.presentation.ui.MovieFragment
import dagger.Component

@FeatureScoped
@Component(dependencies = [MovieDeps::class])
interface MovieComponent {
    @Component.Factory
    interface Factory{
        fun create(deps: MovieDeps) : MovieComponent
    }
    fun injectFragment (movieFragment: MovieFragment)
}

interface MovieDeps : Dependencies {
    val movieInteractor: MovieInteractor
}