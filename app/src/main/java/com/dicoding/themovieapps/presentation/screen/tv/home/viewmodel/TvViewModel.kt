package com.dicoding.themovieapps.presentation.screen.tv.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.themovieapps.domain.usecase.tv.GetTvList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(private val getTvList: GetTvList) : ViewModel() {
    var tvState by mutableStateOf(TvState())
        private set

    init {
        onEvent(TvEvent.OnInitAllTv)
    }

    fun onEvent(tvEvent: TvEvent) {
        when (tvEvent) {
            is TvEvent.OnInitAllTv -> {
                onGetOnTheAirTv()
                onGetPopularTv()
                onGetTopRatedTv()
            }
        }
    }

    private fun onGetOnTheAirTv() {
        viewModelScope.launch{
            tvState = tvState.copy(onTheAirTv = getTvList("on_the_air"))
        }
    }

    private fun onGetPopularTv() {
        viewModelScope.launch {
            tvState = tvState.copy(popularTv = getTvList("popular"))
        }
    }

    private fun onGetTopRatedTv() {
        viewModelScope.launch {
            tvState = tvState.copy(topRatedTv = getTvList("top_rated"))
        }
    }
}