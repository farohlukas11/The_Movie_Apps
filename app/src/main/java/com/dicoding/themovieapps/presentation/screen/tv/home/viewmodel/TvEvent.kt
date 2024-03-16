package com.dicoding.themovieapps.presentation.screen.tv.home.viewmodel

sealed class TvEvent {
    data object OnInitAllTv : TvEvent()

    data class OnInitMessage(val message: String) : TvEvent()

    data object OnRemoveMessageSideEffect : TvEvent()
}