package com.dicoding.themovieapps.presentation.screen.movie.detail.viewmodel

import com.dicoding.themovieapps.domain.model.MovieModel
import com.dicoding.themovieapps.presentation.screen.movie.home.viewmodel.MovieEvent

sealed class MovieDetailEvent {
    data class OnInitRecommendationsMovie(val movieId: Int) : MovieDetailEvent()

    data class OnInitMessage(val message: String) : MovieDetailEvent()

    data object OnRemoveMessageSideEffect : MovieDetailEvent()

    data class OnCheckingMovieIsExist(val id: Int) : MovieDetailEvent()

    data class OnInsertAndUpdateFavouriteNewMovie(val movie: MovieModel) : MovieDetailEvent()

    data class OnUpdateFavouriteMovie(val id: Int, val isFavourite: Boolean) : MovieDetailEvent()

    data class OnGetIsFavouriteMovieStatus(val id: Int) : MovieDetailEvent()
}