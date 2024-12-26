package com.example.data.mapper

import com.example.data.DataConst
import com.example.movie.domain.MovieEntity
import com.example.network.network.dto.response.MovieResponse

fun MovieResponse.asDomain() : MovieEntity {
    return MovieEntity(
        backdropPath = DataConst.IMAGE_PATH_CONST + this.backdropPath,
        budget = this.budget,
        id = this.id,
        originalTitle = this.originalTitle,
        overview = this.overview,
        posterPath = DataConst.IMAGE_PATH_CONST + this.posterPath,
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        status = this.status,
        title = this.title,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}