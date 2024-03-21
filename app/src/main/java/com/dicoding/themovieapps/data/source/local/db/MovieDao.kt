package com.dicoding.themovieapps.data.source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.dicoding.themovieapps.data.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie WHERE series =:series")
    fun getAllMovie(series: String): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovie(movieList: List<MovieEntity>)

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id =:id)")
    fun isMovieExist(id: Int): Flow<Boolean>

    @Query("UPDATE movie SET is_favourite =:isFavourite WHERE id =:id")
    fun updateIsFavouriteMovie(id: Int, isFavourite: Boolean)

    @Query("SELECT (is_favourite) FROM movie WHERE id =:id")
    fun getIsFavouriteMovieStatus(id: Int): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)
}