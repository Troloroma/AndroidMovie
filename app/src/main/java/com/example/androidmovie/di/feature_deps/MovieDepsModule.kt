package com.example.androidmovie.di.feature_deps

import com.example.androidmovie.di.AppComponent
import com.example.core.dagger.DependenciesKey
import com.example.core.dependency.Dependencies
import com.example.movie.presentation.di.MovieDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MovieDepsModule {
    @Binds
    @IntoMap
    @DependenciesKey(MovieDeps::class)
    fun bindMovieDeps(impl: AppComponent): Dependencies
}