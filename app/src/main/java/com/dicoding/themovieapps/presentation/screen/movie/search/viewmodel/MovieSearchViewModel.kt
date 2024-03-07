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
            is MovieSearchEvent.OnQueryChange -> {
                onSearchMovies(movieSearchEvent.query)
            }
        }
    }

    private fun onSearchMovies(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieSearchState = movieSearchState.copy(searchMovies = searchMovie(query))
        }
    }
}