package com.example.androidmovie.di.feature_deps

import com.example.androidmovie.di.AppComponent
import com.example.core.dagger.DependenciesKey
import com.example.core.dependency.Dependencies
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PopularMoviesDepsModule {
    @Binds
    @IntoMap
    @DependenciesKey(PopularMoviesDeps::class)
    fun bindAccountDeps(impl: AppComponent): Dependencies
}
