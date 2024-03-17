package com.dicoding.themovieapps.presentation.screen.movie.detail.viewmodel

import com.dicoding.themovieapps.presentation.screen.movie.home.viewmodel.MovieEvent

sealed class MovieDetailEvent {
    data class OnInitRecommendationsMovie(val movieId: Int) : MovieDetailEvent()

    data class OnInitMessage(val message: String) : MovieDetailEvent()

    data object OnRemoveMessageSideEffect : MovieDetailEvent()
}