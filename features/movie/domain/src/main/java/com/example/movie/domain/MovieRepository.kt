package com.example.movie.domain

import com.example.common.Entity

interface MovieRepository {
    suspend fun getMovie(movieId : Int) : Entity<MovieEntity>
}