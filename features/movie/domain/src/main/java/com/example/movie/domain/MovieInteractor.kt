package com.example.movie.domain

import com.example.common.Entity

interface MovieInteractor {
    suspend fun getMovie(movieId : Int) : Entity<MovieEntity>
}