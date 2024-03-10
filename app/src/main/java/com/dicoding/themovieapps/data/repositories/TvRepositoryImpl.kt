package com.dicoding.themovieapps.data.repositories

import com.dicoding.themovieapps.data.source.NetworkBoundResource
import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.data.source.local.TvLocalDataSource
import com.dicoding.themovieapps.data.source.remote.TvRemoteDataSource
import com.dicoding.themovieapps.data.source.remote.response.ApiResponse
import com.dicoding.themovieapps.data.source.remote.response.TvResponse
import com.dicoding.themovieapps.domain.model.TvModel
import com.dicoding.themovieapps.domain.repositories.TvRepository
import com.dicoding.themovieapps.utils.API_KEY
import com.dicoding.themovieapps.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvRepositoryImpl @Inject constructor(
    private val tvRemoteDataSource: TvRemoteDataSource,
    private val tvLocalDataSource: TvLocalDataSource
) : TvRepository {
    override fun getTvList(series: String): Flow<Resource<List<TvModel>>> =
        object : NetworkBoundResource<List<TvModel>, List<TvResponse>>() {
            override fun loadFromDB(): Flow<List<TvModel>> =
                tvLocalDataSource.getTvList(series).map { listTvEntity ->
                    listTvEntity.map { tvEntity ->
                        DataMapper.mapTvEntityToModel(tvEntity)
                    }
                }

            override suspend fun createCall(): Flow<ApiResponse<List<TvResponse>>> =
                tvRemoteDataSource.getTvList(API_KEY, series)

            override suspend fun saveCallResult(data: List<TvResponse>) {
                val tvListEntity = data.map { tvResponse ->
                    DataMapper.mapTvResponseToEntity(tvResponse, series)
                }
                tvLocalDataSource.insertAllTv(tvListEntity)
            }

            override fun shouldFetch(data: List<TvModel>?): Boolean = data.isNullOrEmpty()
        }.asFlow()

    override fun getTvRecommendations(tvId: Int): Flow<Resource<List<TvModel>>> = flow {
        emit(Resource.Loading())
        when (val response = tvRemoteDataSource.getTvRecommendations(API_KEY, tvId).first()) {
            is ApiResponse.Success -> {
                val tvModelList = response.data.map { tvResponse ->
                    DataMapper.mapTvResponseToModel(tvResponse)
                }
                emit(Resource.Success(tvModelList))
            }

            is ApiResponse.Error -> emit(Resource.Error(response.errorMessage))
            is ApiResponse.Empty -> emit(Resource.Success())
        }
    }

    override fun searchTv(query: String): Flow<Resource<List<TvModel>>> = flow {
        emit(Resource.Loading())
        when (val response = tvRemoteDataSource.searchTv(API_KEY, query).first()) {
            is ApiResponse.Success -> {
                val tvModelList = response.data.map { tvResponse ->
                    DataMapper.mapTvResponseToModel(tvResponse)
                }
                emit(Resource.Success(tvModelList))
            }

            is ApiResponse.Error -> emit(Resource.Error(response.errorMessage))
            is ApiResponse.Empty -> emit(Resource.Success())
        }
    }
}