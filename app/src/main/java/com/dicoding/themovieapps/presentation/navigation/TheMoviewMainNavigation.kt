package com.dicoding.themovieapps.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dicoding.themovieapps.presentation.screen.splash.SplashScreen

@Composable
fun TheMovieMainNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen {
                navController.navigate(Screen.Home.route) {
                    popUpTo(navController.graph.id)
                }
            }
        }

        navigation(route = Screen.Home.route, startDestination = Screen.HomeNavigator.route) {
            composable(Screen.HomeNavigator.route) {
                HomeNavigator()
            }
        }
    }
}