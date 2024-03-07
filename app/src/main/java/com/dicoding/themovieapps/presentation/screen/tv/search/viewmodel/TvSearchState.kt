package com.dicoding.themovieapps.presentation.screen.tv.search.viewmodel

import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.domain.model.TvModel
import kotlinx.coroutines.flow.Flow

data class TvSearchState(
    var searchTv: Flow<Resource<List<TvModel>>>? = null
)
