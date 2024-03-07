package com.dicoding.themovieapps.presentation.screen.tv.home.viewmodel

import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.domain.model.TvModel
import kotlinx.coroutines.flow.Flow

data class TvState(
    val onTheAirTv: Flow<Resource<List<TvModel>>>? = null,
    val popularTv: Flow<Resource<List<TvModel>>>? = null,
    val topRatedTv: Flow<Resource<List<TvModel>>>? = null
)