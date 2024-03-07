package com.dicoding.themovieapps.utils

import com.dicoding.themovieapps.data.entity.MovieEntity
import com.dicoding.themovieapps.data.source.remote.response.MovieResponse
import com.dicoding.themovieapps.domain.model.MovieModel

object DataMapper {
    fun mapMovieEntityToModel(movieEntity: MovieEntity) = MovieModel(
        overview = movieEntity.overview,
        originalLanguage = movieEntity.originalLanguage,
        originalTitle = movieEntity.originalTitle,
        title = movieEntity.title,
        posterPath = movieEntity.posterPath,
        backdropPath = movieEntity.backdropPath,
        releaseDate = movieEntity.releaseDate,
        popularity = movieEntity.popularity,
        voteAverage = movieEntity.voteAverage,
        id = movieEntity.id,
        voteCount = movieEntity.voteCount,
        series = movieEntity.series,
        isFavourite = movieEntity.isFavourite
    )

    fun mapMovieResponseToEntity(movieResponse: MovieResponse, series: String) = MovieEntity(
        overview = movieResponse.overview ?: "",
        originalLanguage = movieResponse.originalLanguage ?: "",
        originalTitle = movieResponse.originalTitle ?: "",
        title = movieResponse.title ?: "",
        posterPath = movieResponse.posterPath ?: "",
        backdropPath = movieResponse.backdropPath ?: "",
        releaseDate = movieResponse.releaseDate ?: "",
        popularity = movieResponse.popularity ?: 0,
        voteAverage = movieResponse.voteAverage ?: 0,
        id = movieResponse.id,
        voteCount = movieResponse.voteCount ?: 0,
        series = series,
    )

    fun mapMovieResponseToModel(movieResponse: MovieResponse) = MovieModel(
        overview = movieResponse.overview,
        originalLanguage = movieResponse.originalLanguage,
        originalTitle = movieResponse.originalTitle,
        title = movieResponse.title,
        posterPath = movieResponse.posterPath,
        backdropPath = movieResponse.backdropPath,
        releaseDate = movieResponse.releaseDate,
        popularity = movieResponse.popularity,
        voteAverage = movieResponse.voteAverage,
        id = movieResponse.id,
        voteCount = movieResponse.voteCount,
    )
}