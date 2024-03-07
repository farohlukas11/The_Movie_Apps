package com.dicoding.themovieapps.di

import android.content.Context
import androidx.room.Room
import com.dicoding.themovieapps.data.source.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "Movies.db")
            .build()


    @Provides
    fun provideMovieDao(appDatabase: AppDatabase) = appDatabase.movieDao()

    @Provides
    fun provideTvDao(appDatabase: AppDatabase) = appDatabase.tvDao()
}