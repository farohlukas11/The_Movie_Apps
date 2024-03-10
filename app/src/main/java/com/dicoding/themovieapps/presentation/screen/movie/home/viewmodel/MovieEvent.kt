package com.dicoding.themovieapps.presentation.screen.movie.home.viewmodel

sealed class MovieEvent {
    data object OnInitAllMovies : MovieEvent()

    data class OnInitMessage(val message: String) : MovieEvent()

    data object OnRemoveMessageSideEffect : MovieEvent()
}