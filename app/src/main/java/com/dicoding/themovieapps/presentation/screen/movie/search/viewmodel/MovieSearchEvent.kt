package com.dicoding.themovieapps.presentation.screen.movie.search.viewmodel

sealed class MovieSearchEvent {

    data class OnQueryChange(val query: String) : MovieSearchEvent()

    data object OnSearching : MovieSearchEvent()

    data class OnInitMessage(val message: String?) : MovieSearchEvent()

    data object OnRemoveMessageSideEffect : MovieSearchEvent()
}