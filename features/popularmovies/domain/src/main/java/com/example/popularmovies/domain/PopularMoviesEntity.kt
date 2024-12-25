package com.example.popularmovies.domain

data class PopularMoviesEntity(
    val page: Int?,
    val results: List<ResultsEntity?>,
    val totalPages: Int?,
    val totalResults: Int?,
)

data class ResultsEntity(
    val adult: Boolean?,
    val backdropPath: String?,
    val genreIds: List<Int?>,
    val id: Int?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)