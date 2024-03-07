package com.dicoding.themovieapps.domain.repositories

import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.domain.model.TvModel
import kotlinx.coroutines.flow.Flow

interface TvRepository {
    fun getTvList(series: String): Flow<Resource<List<TvModel>>>

    fun getTvRecommendations(
        tvId: Int
    ): Flow<Resource<List<TvModel>>>

    fun searchTv(query: String): Flow<Resource<List<TvModel>>>
}