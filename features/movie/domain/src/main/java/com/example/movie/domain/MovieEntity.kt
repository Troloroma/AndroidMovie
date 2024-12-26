package com.example.movie.domain

data class MovieEntity (
    var backdropPath: String?,
    var budget: Int?,
    var id: Int?,
    var originalTitle: String?,
    var overview: String?,
    var posterPath: String?,
    var releaseDate: String?,
    var revenue: Int?,
    var status: String?,
    var title: String?,
    var voteAverage: Double?,
    var voteCount: Int?
)