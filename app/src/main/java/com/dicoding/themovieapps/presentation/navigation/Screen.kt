package com.dicoding.themovieapps.presentation.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash")

    object Home: Screen("home")

    object HomeNavigator: Screen("home-navigator")

    object Movie: Screen("movie")

    object MovieDetail: Screen("detail-movie")

    object MovieSearch: Screen("movie-search")

    object Tv : Screen("tv")

    object TvDetail : Screen("tv-detail")

    object TvSearch: Screen("tv-search")

    object Favourite : Screen("favourite")
}