package com.dicoding.themovieapps.domain.model

data class MovieModel(
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val video: Boolean? = null,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val popularity: Int? = null,
    val voteAverage: Int? = null,
    val id: Int? = null,
    val adult: Boolean? = null,
    val voteCount: Int? = null,
    val isFavourite: Boolean = false,
)
