package com.example.data.repositories

import com.example.common.Entity
import com.example.data.DataConst.LANGUAGE_CONST
import com.example.data.base.BaseRepository
import com.example.data.mapper.asDomain
import com.example.network.network.ApiConst.ACCESS_TOKEN
import com.example.network.network.api.Api
import com.example.network.utils.models.ResponseStatus
import com.example.popularmovies.domain.PopularMoviesEntity
import com.example.popularmovies.domain.PopularMoviesRepository

class PopularMoviesRepositoryImpl(
    private val apiService: Api
) : PopularMoviesRepository, BaseRepository() {
    // TODO token and language
    override suspend fun getPopularMovies(page: Int): Entity<PopularMoviesEntity> {
        return when (
            val response = safeApiSuspendResultNoResponse {
                apiService.getPopularMovies(
                    apiKey = ACCESS_TOKEN,
                    language = LANGUAGE_CONST,
                    page = page
                )
            }) {
            is ResponseStatus.Success -> {
                response.data?.let { data ->
                    map { Entity.Success(data.asDomain()) }
                } ?: run {
                    Entity.Error("Error")
                }
            }
            // Todo errors
            is ResponseStatus.LocalError -> Entity.Error("LocalError")
            is ResponseStatus.ServerError -> Entity.Error("ServerError")
        }
    }
}