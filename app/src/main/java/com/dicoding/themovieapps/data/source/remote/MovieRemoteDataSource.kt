package com.dicoding.themovieapps.data.source.remote

import android.util.Log
import com.dicoding.themovieapps.data.source.remote.api.MovieApiService
import com.dicoding.themovieapps.data.source.remote.response.ApiResponse
import com.dicoding.themovieapps.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

interface MovieRemoteDataSource {
    suspend fun getMovieList(
        apiKey: String,
        series: String
    ): Flow<ApiResponse<List<MovieResponse>>>

    suspend fun getMovieRecommendations(
        apiKey: String,
        movieId: Int
    ): Flow<ApiResponse<List<MovieResponse>>>

    suspend fun searchMovie(apiKey: String, query: String): Flow<ApiResponse<List<MovieResponse>>>
}

@Singleton
class MovieRemoteDataSourceImpl @Inject constructor(private val movieApiService: MovieApiService) :
    MovieRemoteDataSource {

    override suspend fun getMovieList(
        apiKey: String,
        series: String
    ): Flow<ApiResponse<List<MovieResponse>>> = flow {
        try {
            val result = movieApiService.getMovieList(apiKey, series)?.movieList
            emit(if (!result.isNullOrEmpty()) ApiResponse.Success(result) else ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            Log.e(TAG, e.localizedMessage!!)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getMovieRecommendations(
        apiKey: String,
        movieId: Int
    ): Flow<ApiResponse<List<MovieResponse>>> = flow {
        try {
            val result = movieApiService.getMovieRecommendations(apiKey, movieId)?.movieList
            emit(if (!result.isNullOrEmpty()) ApiResponse.Success(result) else ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            Log.e(TAG, e.localizedMessage!!)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun searchMovie(
        apiKey: String,
        query: String
    ): Flow<ApiResponse<List<MovieResponse>>> = flow {
        try {
            val result = movieApiService.searchMovie(apiKey, query)?.movieList
            emit(if (!result.isNullOrEmpty()) ApiResponse.Success(result) else ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            Log.e(TAG, e.localizedMessage!!)
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        val TAG: String = MovieRemoteDataSourceImpl::class.java.simpleName
    }
}