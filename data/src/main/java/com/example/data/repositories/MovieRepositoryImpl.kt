package com.example.data.repositories

import android.util.Log
import com.example.common.Entity
import com.example.data.DataConst
import com.example.data.base.BaseRepository
import com.example.data.mapper.asDomain
import com.example.movie.domain.MovieEntity
import com.example.movie.domain.MovieRepository
import com.example.network.network.ApiConst
import com.example.network.network.api.Api
import com.example.network.utils.models.ResponseStatus

class MovieRepositoryImpl (
    private val apiService: Api
) : BaseRepository(), MovieRepository {
    override suspend fun getMovie(movieId: Int): Entity<MovieEntity> {
        return when (
            val response = safeApiSuspendResultNoResponse {
                apiService.getMovie(
                    apiKey = ApiConst.ACCESS_TOKEN,
                    language = DataConst.LANGUAGE_CONST,
                    movieId = movieId
                )
            }) {
            is ResponseStatus.Success -> {
                Log.d("MovieRepositoryImpl", response.data.toString())
                response.data?.let { data ->
                    map { Entity.Success(data.asDomain()) }
                } ?: run {
                    Entity.Error("Error")
                }
            }
            // Todo errors
            is ResponseStatus.LocalError -> {
                Log.d("MovieRepositoryImpl", "Local")
                Entity.Error("LocalError")
            }
            is ResponseStatus.ServerError -> {
                Log.d("MovieRepositoryImpl", "Server")
                Entity.Error("ServerError")
            }
        }
    }
}