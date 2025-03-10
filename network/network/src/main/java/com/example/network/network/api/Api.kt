package com.example.network.network.api

import com.example.network.network.dto.response.MovieResponse
import com.example.network.network.dto.response.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<PopularMoviesResponse>

    @GET("movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): Response<MovieResponse>
}
