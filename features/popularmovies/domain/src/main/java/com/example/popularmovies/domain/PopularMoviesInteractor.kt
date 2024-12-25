package com.example.popularmovies.domain

import com.example.common.Entity

interface PopularMoviesInteractor {
    suspend fun getPopularMovies(page : Int) : Entity<PopularMoviesEntity>
}