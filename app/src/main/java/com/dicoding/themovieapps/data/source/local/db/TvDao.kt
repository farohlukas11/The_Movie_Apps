package com.dicoding.themovieapps.data.source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.dicoding.themovieapps.data.entity.TvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {
    @Transaction
    @Query("SELECT * FROM tv WHERE series =:series")
    fun getAllTv(series: String): Flow<List<TvEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTv(movieList: List<TvEntity>)
}