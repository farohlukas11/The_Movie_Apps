package com.dicoding.themovieapps.data.source.remote.api

import com.dicoding.themovieapps.data.source.remote.response.MovieResultsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @Headers("Content-Type:application/json")
    @GET("/3/movie/{series}")
    suspend fun getMovieList(
        @Path("series") series: String,
        @Query("api_key") apiKey: String,
    ): MovieResultsResponse?

    @Headers("Content-Type:application/json")
    @GET("/3/movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendations(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): MovieResultsResponse?

    @Headers("Content-Type:application/json")
    @GET("/3/search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): MovieResultsResponse?
}