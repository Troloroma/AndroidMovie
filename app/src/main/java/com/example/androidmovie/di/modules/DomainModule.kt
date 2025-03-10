package com.example.androidmovie.di.modules

import com.example.domain.MovieInteractorImpl
import com.example.domain.PopularMoviesInteractorImpl
import com.example.movie.domain.MovieInteractor
import com.example.movie.domain.MovieRepository
import com.example.popularmovies.domain.PopularMoviesInteractor
import com.example.popularmovies.domain.PopularMoviesRepository
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
class DomainModule {
    @Provides
    fun providePopularMoviesInteractor(
        popularMoviesRepository: PopularMoviesRepository
    ): PopularMoviesInteractor = PopularMoviesInteractorImpl(popularMoviesRepository)

    @Provides
    fun provideMovieInteractor(
        movieRepository: MovieRepository
    ): MovieInteractor = MovieInteractorImpl(movieRepository)
}
