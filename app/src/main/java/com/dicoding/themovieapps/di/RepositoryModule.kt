package com.dicoding.themovieapps.di

import com.dicoding.themovieapps.data.repositories.MovieRepositoryImpl
import com.dicoding.themovieapps.data.repositories.TvRepositoryImpl
import com.dicoding.themovieapps.domain.repositories.MovieRepository
import com.dicoding.themovieapps.domain.repositories.TvRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun provideTvRepository(tvRepositoryImpl: TvRepositoryImpl): TvRepository
}