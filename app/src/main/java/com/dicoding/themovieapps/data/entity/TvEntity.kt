package com.dicoding.themovieapps.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv")
data class TvEntity(
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "original_name")
    val originalName: String,
    @ColumnInfo(name = "popularity")
    val popularity: Int,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "series")
    val series: String,
    @ColumnInfo(name = "is_favourite")
    var isFavourite: Boolean = false
)
