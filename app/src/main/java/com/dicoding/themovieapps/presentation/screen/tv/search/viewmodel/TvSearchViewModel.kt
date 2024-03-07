package com.dicoding.themovieapps.presentation.screen.tv.search.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.themovieapps.domain.usecase.tv.SearchTv
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSearchViewModel @Inject constructor(private val searchTv: SearchTv) : ViewModel() {

    var tvSearchState by mutableStateOf(TvSearchState())

    fun onEvent(tvSearchEvent: TvSearchEvent) {
        when (tvSearchEvent) {
            is TvSearchEvent.OnQueryChange -> {
                onSearchTv(tvSearchEvent.query)
            }
        }
    }

    private fun onSearchTv(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            tvSearchState = tvSearchState.copy(searchTv = searchTv(query))
        }
    }
}