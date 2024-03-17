package com.dicoding.themovieapps.presentation.screen.movie.detail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.themovieapps.domain.usecase.movie.GetMovieRecommendations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getMovieRecommendations: GetMovieRecommendations) :
    ViewModel() {

    var movieDetailState by mutableStateOf(MovieDetailState())
        private set

    fun onEvent(movieDetailEvent: MovieDetailEvent) {
        when (movieDetailEvent) {
            is MovieDetailEvent.OnInitRecommendationsMovie -> onInitRecommendationsMovie(
                movieDetailEvent.movieId
            )

            is MovieDetailEvent.OnInitMessage -> onInitMessage(movieDetailEvent.message)
            is MovieDetailEvent.OnRemoveMessageSideEffect -> onRemoveMessageSideEffect()
        }
    }

    private fun onInitRecommendationsMovie(movieId: Int) {
        viewModelScope.launch {
            movieDetailState =
                movieDetailState.copy(movieRecommendation = getMovieRecommendations(movieId))
        }
    }

    private fun onInitMessage(message: String) {
        movieDetailState = movieDetailState.copy(message = message)
    }

    private fun onRemoveMessageSideEffect() {
        movieDetailState.message = null
    }
}