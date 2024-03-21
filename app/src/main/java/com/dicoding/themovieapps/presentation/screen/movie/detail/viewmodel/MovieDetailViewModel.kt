package com.dicoding.themovieapps.presentation.screen.movie.detail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.themovieapps.domain.model.MovieModel
import com.dicoding.themovieapps.domain.usecase.movie.GetIsFavouriteMovieStatus
import com.dicoding.themovieapps.domain.usecase.movie.GetMovieRecommendations
import com.dicoding.themovieapps.domain.usecase.movie.InsertMovie
import com.dicoding.themovieapps.domain.usecase.movie.IsMovieExist
import com.dicoding.themovieapps.domain.usecase.movie.UpdateIsFavouriteMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieRecommendations: GetMovieRecommendations,
    private val isMovieExist: IsMovieExist,
    private val updateIsFavouriteMovie: UpdateIsFavouriteMovie,
    private val insertMovie: InsertMovie,
    private val getIsFavouriteMovieStatus: GetIsFavouriteMovieStatus
) :
    ViewModel() {

    var movieDetailState by mutableStateOf(MovieDetailState())
        private set

    fun onEvent(movieDetailEvent: MovieDetailEvent) {
        when (movieDetailEvent) {
            is MovieDetailEvent.OnInitRecommendationsMovie -> onInitRecommendationsMovie(
                movieDetailEvent.movieId
            )

            is MovieDetailEvent.OnCheckingMovieIsExist -> onCheckingMovieIsExist(movieDetailEvent.id)

            is MovieDetailEvent.OnInsertAndUpdateFavouriteNewMovie -> onInsertAndUpdateFavouriteNewMovie(
                movieDetailEvent.movie
            )

            is MovieDetailEvent.OnUpdateFavouriteMovie -> onUpdateFavouriteMovie(
                movieDetailEvent.id,
                movieDetailEvent.isFavourite
            )

            is MovieDetailEvent.OnGetIsFavouriteMovieStatus -> onGetIsFavouriteMovieStatus(
                movieDetailEvent.id
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

    private fun onCheckingMovieIsExist(id: Int) {
        viewModelScope.launch {
            isMovieExist(id).collect { isExist ->
                movieDetailState = movieDetailState.copy(movieIsExist = isExist)
            }
        }
    }

    private fun onInsertAndUpdateFavouriteNewMovie(movie: MovieModel) {
        viewModelScope.launch {
            insertMovie(movie)
            onUpdateFavouriteMovie(movie.id, !movieDetailState.isFavourite)
        }
    }

    private fun onUpdateFavouriteMovie(id: Int, isFavourite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updateIsFavouriteMovie(id, isFavourite)
        }
    }

    private fun onGetIsFavouriteMovieStatus(id: Int) {
        viewModelScope.launch {
            getIsFavouriteMovieStatus(id).collect { isFavourite ->
                movieDetailState = movieDetailState.copy(isFavourite = isFavourite)
            }
        }
    }

    private fun onInitMessage(message: String) {
        movieDetailState = movieDetailState.copy(message = message)
    }

    private fun onRemoveMessageSideEffect() {
        movieDetailState.message = null
    }
}