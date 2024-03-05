package com.dicoding.themovieapps.data.entity

import androidx.room.Entity

@Entity
data class MovieEntity(
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val popularity: Int,
    val voteAverage: Int,
    val id: Int,
    val voteCount: Int,
    val isFavourite: Boolean,
)
