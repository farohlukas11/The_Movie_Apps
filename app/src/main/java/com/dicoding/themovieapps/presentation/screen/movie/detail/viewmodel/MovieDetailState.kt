package com.dicoding.themovieapps.presentation.screen.movie.detail.viewmodel

import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

data class MovieDetailState(
    var movieRecommendation: Flow<Resource<List<MovieModel>>>? = null,
    var message: String? = null
)
