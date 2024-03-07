package com.dicoding.themovieapps.domain.usecase.tv

import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.domain.model.TvModel
import com.dicoding.themovieapps.domain.repositories.TvRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTvRecommendations @Inject constructor(private val tvRepository: TvRepository) {

    operator fun invoke(tvId: Int): Flow<Resource<List<TvModel>>> =
        tvRepository.getTvRecommendations(tvId)
}