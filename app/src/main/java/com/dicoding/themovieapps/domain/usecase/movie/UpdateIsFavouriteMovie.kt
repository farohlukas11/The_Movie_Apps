package com.dicoding.themovieapps.domain.usecase.movie

import com.dicoding.themovieapps.domain.repositories.MovieRepository
import javax.inject.Inject

class UpdateIsFavouriteMovie @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(id: Int, isFavourite: Boolean) =
        movieRepository.updateIsFavouriteMovie(id, isFavourite)
}