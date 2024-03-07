package com.dicoding.themovieapps.presentation.screen.movie.home.viewmodel

sealed class MovieEvent {
    data object OnInitAllMovies : MovieEvent()
}