package com.dicoding.themovieapps.data.repositories

import com.dicoding.themovieapps.data.source.NetworkBoundResource
import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.data.source.local.MovieLocalDataSource
import com.dicoding.themovieapps.data.source.remote.MovieRemoteDataSource
import com.dicoding.themovieapps.data.source.remote.response.ApiResponse
import com.dicoding.themovieapps.data.source.remote.response.MovieResponse
import com.dicoding.themovieapps.domain.model.MovieModel
import com.dicoding.themovieapps.domain.repositories.MovieRepository
import com.dicoding.themovieapps.data.utils.API_KEY
import com.dicoding.themovieapps.data.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) : MovieRepository {
    override fun getMovieList(series: String): Flow<Resource<List<MovieModel>>> =
        object : NetworkBoundResource<List<MovieModel>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<MovieModel>> =
                movieLocalDataSource.getMovieList(series).map { listMovieEntity ->
                    listMovieEntity.map { movieEntity ->
                        DataMapper.mapMovieEntityToModel(movieEntity)
                    }
                }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                movieRemoteDataSource.getMovieList(API_KEY, series)


            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieListEntity = data.map { movieResponse ->
                    DataMapper.mapMovieResponseToEntity(movieResponse, series)
                }
                movieLocalDataSource.insertAllMovie(movieListEntity)
            }

            override fun shouldFetch(data: List<MovieModel>?): Boolean = data.isNullOrEmpty()
        }.asFlow()

    override fun getMovieRecommendations(movieId: Int): Flow<Resource<List<MovieModel>>> = flow {
        emit(Resource.Loading())
        when (val response = movieRemoteDataSource.getMovieRecommendations(API_KEY, movieId).first()) {
            is ApiResponse.Success -> {
                val movieModelList = response.data.map { movieResponse ->
                    DataMapper.mapMovieResponseToModel(movieResponse)
                }
                emit(Resource.Success(movieModelList))
            }

            is ApiResponse.Error -> emit(Resource.Error(response.errorMessage))
            is ApiResponse.Empty -> emit(Resource.Success())
        }
    }

    override fun searchMovie(query: String): Flow<Resource<List<MovieModel>>> = flow {
        emit(Resource.Loading())
        when (val response = movieRemoteDataSource.searchMovie(API_KEY, query).first()) {
            is ApiResponse.Success -> {
                val movieModelList = response.data.map { movieResponse ->
                    DataMapper.mapMovieResponseToModel(movieResponse)
                }
                emit(Resource.Success(movieModelList))
            }

            is ApiResponse.Error -> emit(Resource.Error(response.errorMessage))
            is ApiResponse.Empty -> emit(Resource.Success())
        }
    }
}