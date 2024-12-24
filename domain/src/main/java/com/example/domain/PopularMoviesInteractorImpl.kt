package com.example.domain

import com.example.popularmovies.domain.PopularMoviesInteractor
import com.example.popularmovies.domain.PopularMoviesRepository

class PopularMoviesInteractorImpl(
    private val popularMoviesRepository: PopularMoviesRepository
) : PopularMoviesInteractor {

}