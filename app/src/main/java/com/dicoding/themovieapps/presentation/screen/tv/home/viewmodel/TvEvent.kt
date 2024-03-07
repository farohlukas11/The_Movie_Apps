package com.dicoding.themovieapps.presentation.screen.tv.home.viewmodel

sealed class TvEvent {
    data object OnInitAllTv : TvEvent()
}