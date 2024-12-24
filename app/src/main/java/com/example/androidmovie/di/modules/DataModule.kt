package com.example.androidmovie.di.modules

import com.example.data.repositories.PopularMoviesRepositoryImpl
import com.example.network.network.api.Api
import com.example.popularmovies.domain.PopularMoviesRepository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class DataModule {
    @Provides
    fun providePopularMoviesRepository(
        api: Api
    ) : PopularMoviesRepository = PopularMoviesRepositoryImpl(
        api
    )
}