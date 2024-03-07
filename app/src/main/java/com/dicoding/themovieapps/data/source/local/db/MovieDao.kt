package com.dicoding.themovieapps.data.source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.dicoding.themovieapps.data.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Transaction
    @Query("SELECT * FROM movie WHERE series =:series")
    fun getAllMovie(series: String): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovie(movieList: List<MovieEntity>)
}