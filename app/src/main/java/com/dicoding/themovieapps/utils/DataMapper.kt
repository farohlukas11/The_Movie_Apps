package com.dicoding.themovieapps.utils

import com.dicoding.themovieapps.data.entity.MovieEntity
import com.dicoding.themovieapps.data.entity.TvEntity
import com.dicoding.themovieapps.data.source.remote.response.MovieResponse
import com.dicoding.themovieapps.data.source.remote.response.TvResponse
import com.dicoding.themovieapps.domain.model.MovieModel
import com.dicoding.themovieapps.domain.model.TvModel

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

    fun mapTvEntityToModel(tvEntity: TvEntity) = TvModel(
        backdropPath = tvEntity.backdropPath,
        firstAirDate = tvEntity.firstAirDate,
        overview = tvEntity.overview,
        originalLanguage = tvEntity.originalLanguage,
        originalName = tvEntity.originalName,
        popularity = tvEntity.popularity,
        voteAverage = tvEntity.voteAverage,
        name = tvEntity.name,
        id = tvEntity.id,
        voteCount = tvEntity.voteCount,
        posterPath = tvEntity.posterPath,
        series = tvEntity.series,
        isFavourite = tvEntity.isFavourite
    )

    fun mapTvResponseToEntity(tvResponse: TvResponse, series: String) = TvEntity(
        backdropPath = tvResponse.backdropPath ?: "",
        firstAirDate = tvResponse.firstAirDate ?: "",
        overview = tvResponse.overview ?: "",
        originalLanguage = tvResponse.originalLanguage ?: "",
        originalName = tvResponse.originalName ?: "",
        popularity = tvResponse.popularity ?: 0,
        voteAverage = tvResponse.voteAverage ?: 0,
        name = tvResponse.name ?: "",
        id = tvResponse.id,
        voteCount = tvResponse.voteCount ?: 0,
        posterPath = tvResponse.posterPath ?: "",
        series = series
    )

    fun mapTvResponseToModel(tvResponse: TvResponse) = TvModel(
        backdropPath = tvResponse.backdropPath,
        firstAirDate = tvResponse.firstAirDate,
        overview = tvResponse.overview ,
        originalLanguage = tvResponse.originalLanguage,
        originalName = tvResponse.originalName,
        popularity = tvResponse.popularity,
        voteAverage = tvResponse.voteAverage,
        name = tvResponse.name,
        id = tvResponse.id,
        voteCount = tvResponse.voteCount,
        posterPath = tvResponse.posterPath,
    )
}