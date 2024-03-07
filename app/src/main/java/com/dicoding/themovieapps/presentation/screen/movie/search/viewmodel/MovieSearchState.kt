package com.dicoding.themovieapps.presentation.screen.movie.search.viewmodel

import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

data class MovieSearchState(
    var searchMovies: Flow<Resource<List<MovieModel>>>? = null
)
