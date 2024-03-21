package com.dicoding.themovieapps.domain.repositories

import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieList(
        series: String
    ): Flow<Resource<List<MovieModel>>>

    fun getMovieRecommendations(
        movieId: Int
    ): Flow<Resource<List<MovieModel>>>

    fun searchMovie(query: String): Flow<Resource<List<MovieModel>>>

    fun isMovieExist(id: Int): Flow<Boolean>

    fun updateIsFavouriteMovie(id: Int, isFavourite: Boolean)

    fun getIsFavouriteMovieStatus(id: Int): Flow<Boolean>

    suspend fun insertMovie(movie: MovieModel)
}