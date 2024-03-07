package com.dicoding.themovieapps.presentation.screen.movie.search.viewmodel

sealed class MovieSearchEvent {

    data class OnQueryChange(val query: String) : MovieSearchEvent()
}