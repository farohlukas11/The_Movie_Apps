package com.dicoding.themovieapps.data.source.local

import com.dicoding.themovieapps.data.entity.MovieEntity
import com.dicoding.themovieapps.data.source.local.db.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface MovieLocalDataSource {
    fun getMovieList(series: String): Flow<List<MovieEntity>>

    suspend fun insertAllMovie(movieList: List<MovieEntity>)

    fun isMovieExist(id: Int): Flow<Boolean>

    fun updateIsFavouriteMovie(id: Int, isFavourite: Boolean)

    fun getIsFavouriteMovieStatus(id: Int): Flow<Boolean>

    suspend fun insertMovie(movie: MovieEntity)
}

@Singleton
class MovieLocalDataSourceImpl @Inject constructor(private val movieDao: MovieDao) :
    MovieLocalDataSource {
    override fun getMovieList(series: String): Flow<List<MovieEntity>> =
        movieDao.getAllMovie(series)

    override suspend fun insertAllMovie(movieList: List<MovieEntity>) =
        movieDao.insertAllMovie(movieList)

    override fun isMovieExist(id: Int): Flow<Boolean> = movieDao.isMovieExist(id)

    override fun updateIsFavouriteMovie(id: Int, isFavourite: Boolean) =
        movieDao.updateIsFavouriteMovie(id, isFavourite)

    override fun getIsFavouriteMovieStatus(id: Int): Flow<Boolean> =
        movieDao.getIsFavouriteMovieStatus(id)


    override suspend fun insertMovie(movie: MovieEntity) = movieDao.insertMovie(movie)

}