package com.dicoding.themovieapps.data.source.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TvResultsResponse(
    @field:SerializedName("results")
    val tvList: List<TvResponse> = emptyList()
)

@Parcelize
data class TvResponse(

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("original_name")
    val originalName: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null
) : Parcelable
