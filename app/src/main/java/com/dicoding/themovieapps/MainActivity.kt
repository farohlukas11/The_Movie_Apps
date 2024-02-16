package com.dicoding.themovieapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.dicoding.themovieapps.presentation.navigation.TheMovieMainNavigation
import com.dicoding.themovieapps.ui.theme.TheMovieAppsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovieAppsTheme {
               Surface {
                   val navController = rememberNavController()
                   TheMovieMainNavigation(navController = navController)
               }
            }
        }
    }
}