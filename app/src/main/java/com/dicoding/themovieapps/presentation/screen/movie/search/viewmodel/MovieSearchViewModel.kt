package com.dicoding.themovieapps.presentation.screen.movie.search.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.themovieapps.domain.usecase.movie.SearchMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(private val searchMovie: SearchMovie) : ViewModel() {

    var movieSearchState by mutableStateOf(MovieSearchState())
        private set

    fun onEvent(movieSearchEvent: MovieSearchEvent) {
        when (movieSearchEvent) {
            is MovieSearchEvent.OnQueryChange -> onQueryChange(movieSearchEvent.query)
            is MovieSearchEvent.OnSearching -> {
                if (movieSearchState.searchText.isNotBlank()) onSearchingMovies()
                else onInitMessage("Input Masih Kosong!")
            }

            is MovieSearchEvent.OnInitMessage -> onInitMessage(movieSearchEvent.message)
            is MovieSearchEvent.OnRemoveMessageSideEffect -> onRemoveMessageSideEffect()
        }
    }

    private fun onQueryChange(query: String) {
        movieSearchState = movieSearchState.copy(searchText = query)
    }

    private fun onSearchingMovies() {
        viewModelScope.launch {
            movieSearchState =
                movieSearchState.copy(searchMovies = searchMovie(movieSearchState.searchText))
        }
    }

    private fun onInitMessage(message: String) {
        movieSearchState = movieSearchState.copy(message = message)
    }

    private fun onRemoveMessageSideEffect() {
        movieSearchState.message = null
    }
}