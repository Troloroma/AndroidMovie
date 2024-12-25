package com.example.domain

import com.example.common.Entity
import com.example.popularmovies.domain.PopularMoviesEntity
import com.example.popularmovies.domain.PopularMoviesInteractor
import com.example.popularmovies.domain.PopularMoviesRepository

class PopularMoviesInteractorImpl(
    private val popularMoviesRepository: PopularMoviesRepository
) : PopularMoviesInteractor {
    override suspend fun getPopularMovies(page: Int): Entity<PopularMoviesEntity> {
        return popularMoviesRepository.getPopularMovies(page)
    }
}