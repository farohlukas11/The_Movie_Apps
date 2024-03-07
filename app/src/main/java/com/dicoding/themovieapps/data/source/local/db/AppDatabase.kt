package com.dicoding.themovieapps.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.themovieapps.data.entity.MovieEntity
import com.dicoding.themovieapps.data.entity.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    abstract fun tvDao(): TvDao
}