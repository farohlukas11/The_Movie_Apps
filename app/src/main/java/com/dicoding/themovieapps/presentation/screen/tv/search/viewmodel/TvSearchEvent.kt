package com.dicoding.themovieapps.presentation.screen.tv.search.viewmodel

sealed class TvSearchEvent {

    data class OnQueryChange(val query: String) : TvSearchEvent()
}