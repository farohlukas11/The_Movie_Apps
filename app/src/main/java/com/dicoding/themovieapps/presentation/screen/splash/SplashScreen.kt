package com.dicoding.themovieapps.presentation.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    toHome: () -> Unit
) {
    val delayTime = 2000L
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "The Movie Apps",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
            )
        }

        LaunchedEffect(Unit) {
            delay(delayTime)
            toHome.invoke()
        }
    }
}