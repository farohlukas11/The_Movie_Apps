package com.dicoding.themovieapps.data.source.remote.api

import com.dicoding.themovieapps.data.source.remote.response.MovieResultsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/{series}")
    fun getMovieList(
        @Query("api_key") apiKey: String,
        @Path("series") series: String,
    ): MovieResultsResponse?

    @GET("movie/{movie_id}/recommendations")
    fun getMovieRecommendations(
        @Query("api_key") apiKey: String,
        @Path("movie_id") movieId: Int,
    ): MovieResultsResponse?

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): MovieResultsResponse?
}