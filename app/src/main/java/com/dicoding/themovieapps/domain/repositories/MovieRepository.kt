package com.dicoding.themovieapps.domain.repositories

import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieList(
        series: String
    ): Flow<Resource<List<MovieModel>>>

    fun getMoveRecommendations(
        movieId: Int
    ): Flow<Resource<List<MovieModel>>>
}