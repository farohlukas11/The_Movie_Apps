package com.dicoding.themovieapps.data.source.remote.api

import com.dicoding.themovieapps.data.source.remote.response.TvResultsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvApiService {

    @GET("tv/{series}")
    fun getTvList(
        @Query("api_key") apiKey: String,
        @Path("series") series: String,
    ): TvResultsResponse?

    @GET("tv/{tv_id}/recommendations")
    fun getTvRecommendations(
        @Query("api_key") apiKey: String,
        @Path("tv_id") movieId: Int,
    ): TvResultsResponse?

    @GET("search/tv")
    fun searchTv(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): TvResultsResponse?
}