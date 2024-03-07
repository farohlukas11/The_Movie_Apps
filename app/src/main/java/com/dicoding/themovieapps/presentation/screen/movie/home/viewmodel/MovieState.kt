package com.dicoding.themovieapps.presentation.screen.movie.home.viewmodel

import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

data class MovieState(
    var upcomingMovies: Flow<Resource<List<MovieModel>>>? = null,
    var popularMovies: Flow<Resource<List<MovieModel>>>? = null,
    var topRatedMovies: Flow<Resource<List<MovieModel>>>? = null,
)
