package com.example.data.mapper

import com.example.data.ImagePathConst
import com.example.network.network.dto.response.PopularMoviesResponse
import com.example.network.network.dto.response.Results
import com.example.popularmovies.domain.PopularMoviesEntity
import com.example.popularmovies.domain.ResultsEntity

fun PopularMoviesResponse.asDomain(): PopularMoviesEntity {
    return PopularMoviesEntity(
        page = this.page,
        results = this.results.map { it.asDomain() }.toList(),
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}

fun Results.asDomain(): ResultsEntity {
    return ResultsEntity(
        adult = this.adult,
        backdropPath = ImagePathConst.IMAGE_PATH_CONST + this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = ImagePathConst.IMAGE_PATH_CONST + this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}