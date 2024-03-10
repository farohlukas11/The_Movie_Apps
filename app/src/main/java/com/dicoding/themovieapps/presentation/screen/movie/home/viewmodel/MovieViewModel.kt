package com.dicoding.themovieapps.presentation.screen.movie.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.themovieapps.domain.usecase.movie.GetMovieList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val getMovieList: GetMovieList) : ViewModel() {

    var movieState by mutableStateOf(MovieState())
        private set

    init {
        onEvent(MovieEvent.OnInitAllMovies)
    }

    fun onEvent(movieEvent: MovieEvent) {
        when (movieEvent) {
            is MovieEvent.OnInitAllMovies -> {
                onGetUpcomingMovies()
                onGetPopularMovies()
                onGetTopRatedMovies()
            }

            is MovieEvent.OnInitMessage -> onInitMessage(movieEvent.message)
            is MovieEvent.OnRemoveMessageSideEffect -> onRemoveMessageSideEffect()
        }
    }

    private fun onGetUpcomingMovies() {
        viewModelScope.launch {
            movieState = movieState.copy(upcomingMovies = getMovieList("upcoming"))
        }
    }

    private fun onGetPopularMovies() {
        viewModelScope.launch {
            movieState = movieState.copy(popularMovies = getMovieList("popular"))
        }
    }

    private fun onGetTopRatedMovies() {
        viewModelScope.launch {
            movieState = movieState.copy(topRatedMovies = getMovieList("top_rated"))
        }
    }

    private fun onInitMessage(message: String) {
        movieState = movieState.copy(message = message)
    }

    private fun onRemoveMessageSideEffect() {
        movieState.message = null
    }
}