package com.dicoding.themovieapps.domain.usecase.movie

import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.domain.model.MovieModel
import com.dicoding.themovieapps.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovie @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(query: String): Flow<Resource<List<MovieModel>>> =
        movieRepository.searchMovie(query)
}