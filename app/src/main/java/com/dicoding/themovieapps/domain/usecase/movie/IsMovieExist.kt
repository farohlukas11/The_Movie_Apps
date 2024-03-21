package com.dicoding.themovieapps.domain.usecase.movie

import com.dicoding.themovieapps.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsMovieExist @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(id: Int): Flow<Boolean> = movieRepository.isMovieExist(id)
}