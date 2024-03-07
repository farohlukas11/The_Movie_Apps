package com.dicoding.themovieapps.data.source.local

import com.dicoding.themovieapps.data.entity.MovieEntity
import com.dicoding.themovieapps.data.source.local.db.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface MovieLocalDataSource {
    fun getMovieList(series: String): Flow<List<MovieEntity>>

    suspend fun insertAllMovie(movieList: List<MovieEntity>)
}

@Singleton
class MovieLocalDataSourceImpl @Inject constructor(private val movieDao: MovieDao) :
    MovieLocalDataSource {
    override fun getMovieList(series: String): Flow<List<MovieEntity>> =
        movieDao.getAllMovie(series)

    override suspend fun insertAllMovie(movieList: List<MovieEntity>) =
        movieDao.insertAllMovie(movieList)
}