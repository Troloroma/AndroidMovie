package com.example.androidmovie.di.feature_deps

import com.example.androidmovie.di.AppComponent
import com.example.core.dagger.DependenciesKey
import com.example.core.dependency.Dependencies
import com.example.popularmovies.presentation.di.PopularMoviesDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PopularMoviesDepsModule {
    @Binds
    @IntoMap
    @DependenciesKey(PopularMoviesDeps::class)
    fun bindPopularMoviesDeps(impl: AppComponent): Dependencies
}
