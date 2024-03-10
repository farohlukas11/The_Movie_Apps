package com.dicoding.themovieapps.domain.model

class TvModel(
    val backdropPath: String? = null,
    val firstAirDate: String? = null,
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val popularity: Double? = null,
    val voteAverage: Double? = null,
    val name: String? = null,
    val id: Int,
    val voteCount: Int? = null,
    val posterPath: String? = null,
    val series: String? = null,
    var isFavourite: Boolean = false
)