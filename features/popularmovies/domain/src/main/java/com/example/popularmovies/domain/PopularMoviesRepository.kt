package com.example.popularmovies.domain

import com.example.common.Entity

interface PopularMoviesRepository {
    suspend fun getPopularMovies(page: Int) : Entity<PopularMoviesEntity>
}