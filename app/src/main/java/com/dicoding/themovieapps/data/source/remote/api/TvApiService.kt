package com.dicoding.themovieapps.data.source.remote.api

import com.dicoding.themovieapps.data.source.remote.response.TvResultsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvApiService {

    @GET("/3/tv/{series}")
    suspend fun getTvList(
        @Path("series") series: String,
        @Query("api_key") apiKey: String,
    ): TvResultsResponse?

    @GET("/3/tv/{tv_id}/recommendations")
    suspend fun getTvRecommendations(
        @Path("tv_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): TvResultsResponse?

    @GET("/3/search/tv")
    suspend fun searchTv(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): TvResultsResponse?
}