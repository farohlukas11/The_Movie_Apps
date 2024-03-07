package com.dicoding.themovieapps.domain.model

import com.google.gson.annotations.SerializedName

class TvModel(
    val backdropPath: String? = null,
    val firstAirDate: String? = null,
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val popularity: Int? = null,
    val voteAverage: Int? = null,
    val name: String? = null,
    val id: Int,
    val voteCount: Int? = null,
    val posterPath: String? = null,
    val series: String? = null,
    var isFavourite: Boolean = false
)