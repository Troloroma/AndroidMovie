package com.example.domain

import com.example.common.Entity
import com.example.movie.domain.MovieEntity
import com.example.movie.domain.MovieInteractor
import com.example.movie.domain.MovieRepository

class MovieInteractorImpl(
    private val movieRepository: MovieRepository
) : MovieInteractor {
    override suspend fun getMovie(movieId: Int): Entity<MovieEntity> {
        return movieRepository.getMovie(movieId)
    }

}