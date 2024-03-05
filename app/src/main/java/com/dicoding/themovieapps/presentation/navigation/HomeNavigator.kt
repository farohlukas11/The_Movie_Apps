package com.dicoding.themovieapps.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dicoding.themovieapps.domain.model.BottomNavigationItem
import com.dicoding.themovieapps.presentation.screen.favourite.FavouriteScreen
import com.dicoding.themovieapps.presentation.screen.movie.detail.MovieDetailScreen
import com.dicoding.themovieapps.presentation.screen.movie.home.MovieScreen
import com.dicoding.themovieapps.presentation.screen.movie.search.MovieSearchScreen
import com.dicoding.themovieapps.presentation.screen.profile.ProfileScreen
import com.dicoding.themovieapps.presentation.screen.tv.detail.TvDetailScreen
import com.dicoding.themovieapps.presentation.screen.tv.home.TvScreen
import com.dicoding.themovieapps.presentation.screen.tv.search.TvSearchScreen

@Composable
fun HomeNavigator() {
    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = Icons.Default.Movie, text = "Movie"),
            BottomNavigationItem(icon = Icons.Default.Tv, text = "Tv"),
            BottomNavigationItem(icon = Icons.Default.Favorite, text = "Favourite"),
            BottomNavigationItem(icon = Icons.Default.Person, text = "Profile"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = when (backStackState?.destination?.route) {
        Screen.Movie.route -> 0
        Screen.Tv.route -> 1
        Screen.Favourite.route -> 2
        Screen.Profile.route -> 3
        else -> 0
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        val state = backStackState?.destination?.route
        state == Screen.Movie.route ||
                state == Screen.Tv.route ||
                state == Screen.Favourite.route ||
                state == Screen.Profile.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                TheMovieBottomNavigation(
                    items = bottomNavigationItem,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(navController, Screen.Movie.route)
                            1 -> navigateToTab(navController, Screen.Tv.route)
                            2 -> navigateToTab(navController, Screen.Favourite.route)
                            3 -> navigateToTab(navController, Screen.Profile.route)
                        }
                    })
            }
        }
    ) { paddingValues ->
        val bottomPadding = paddingValues.calculateBottomPadding()

        NavHost(
            navController = navController,
            startDestination = Screen.Movie.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(Screen.Movie.route) {
                MovieScreen()
            }
            composable(Screen.MovieDetail.route) {
                MovieDetailScreen()
            }
            composable(Screen.MovieSearch.route) {
                MovieSearchScreen()
            }
            composable(Screen.Tv.route) {
                TvScreen()
            }
            composable(Screen.TvDetail.route) {
                TvDetailScreen()
            }
            composable(Screen.TvSearch.route) {
                TvSearchScreen()
            }
            composable(Screen.Favourite.route) {
                FavouriteScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screeRoute ->
            popUpTo(screeRoute) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}