package com.dicoding.themovieapps.domain.usecase.movie

import com.dicoding.themovieapps.domain.model.MovieModel
import com.dicoding.themovieapps.domain.repositories.MovieRepository
import javax.inject.Inject

class InsertMovie @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(movie: MovieModel) = movieRepository.insertMovie(movie)
}