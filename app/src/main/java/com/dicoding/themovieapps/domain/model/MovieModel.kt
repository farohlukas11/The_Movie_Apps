package com.dicoding.themovieapps.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val popularity: Double? = null,
    val voteAverage: Double? = null,
    val id: Int,
    val voteCount: Int? = null,
    val series: String? = null,
    var isFavourite: Boolean = false,
) : Parcelable
