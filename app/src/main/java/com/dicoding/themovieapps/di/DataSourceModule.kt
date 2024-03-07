package com.dicoding.themovieapps.di

import com.dicoding.themovieapps.data.source.local.MovieLocalDataSource
import com.dicoding.themovieapps.data.source.local.MovieLocalDataSourceImpl
import com.dicoding.themovieapps.data.source.local.TvLocalDataSource
import com.dicoding.themovieapps.data.source.local.TvLocalDataSourceImpl
import com.dicoding.themovieapps.data.source.remote.MovieRemoteDataSource
import com.dicoding.themovieapps.data.source.remote.MovieRemoteDataSourceImpl
import com.dicoding.themovieapps.data.source.remote.TvRemoteDataSource
import com.dicoding.themovieapps.data.source.remote.TvRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideMovieRemoteDataSource(movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @Binds
    abstract fun provideTvRemoteDataSource(tvRemoteDataSourceImpl: TvRemoteDataSourceImpl): TvRemoteDataSource

    @Binds
    abstract fun provideMovieLocalDataSource(movieLocalDataSourceImpl: MovieLocalDataSourceImpl): MovieLocalDataSource

    @Binds
    abstract fun provideTvLocalDataSource(tvLocalDataSourceImpl: TvLocalDataSourceImpl): TvLocalDataSource
}