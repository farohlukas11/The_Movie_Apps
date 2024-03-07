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
        }
    }

    private fun onGetUpcomingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieState = movieState.copy(upcomingMovies = getMovieList("upcoming"))
        }
    }

    private fun onGetPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieState = movieState.copy(popularMovies = getMovieList("popular"))
        }
    }

    private fun onGetTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieState = movieState.copy(topRatedMovies = getMovieList("top_rated"))
        }
    }
}