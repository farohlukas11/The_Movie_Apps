package com.dicoding.themovieapps.data.source.remote

import android.util.Log
import com.dicoding.themovieapps.data.source.remote.api.TvApiService
import com.dicoding.themovieapps.data.source.remote.response.ApiResponse
import com.dicoding.themovieapps.data.source.remote.response.TvResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

interface TvRemoteDataSource {
    suspend fun getTvList(apiKey: String, series: String): Flow<ApiResponse<List<TvResponse>>>

    suspend fun getTvRecommendations(
        apiKey: String,
        tvId: Int
    ): Flow<ApiResponse<List<TvResponse>>>

    suspend fun searchTv(apiKey: String, query: String): Flow<ApiResponse<List<TvResponse>>>
}

@Singleton
class TvRemoteDataSourceImpl @Inject constructor(private val tvApiService: TvApiService) :
    TvRemoteDataSource {
    override suspend fun getTvList(
        apiKey: String,
        series: String
    ): Flow<ApiResponse<List<TvResponse>>> = flow {
        try {
            val result = tvApiService.getTvList(series, apiKey)?.tvList
            emit(if (!result.isNullOrEmpty()) ApiResponse.Success(result) else ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            Log.e(TAG, e.localizedMessage!!)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getTvRecommendations(
        apiKey: String,
        tvId: Int
    ): Flow<ApiResponse<List<TvResponse>>> = flow {
        try {
            val result = tvApiService.getTvRecommendations(tvId, apiKey)?.tvList
            emit(if (!result.isNullOrEmpty()) ApiResponse.Success(result) else ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            Log.e(TAG, e.localizedMessage!!)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun searchTv(
        apiKey: String,
        query: String
    ): Flow<ApiResponse<List<TvResponse>>> = flow {
        try {
            val result = tvApiService.searchTv(apiKey, query)?.tvList
            emit(if (!result.isNullOrEmpty()) ApiResponse.Success(result) else ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            Log.e(TAG, e.localizedMessage!!)
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        val TAG: String = TvRemoteDataSourceImpl::class.java.simpleName
    }
}