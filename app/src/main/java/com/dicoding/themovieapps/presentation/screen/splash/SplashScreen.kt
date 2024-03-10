package com.dicoding.themovieapps.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.themovieapps.R
import com.dicoding.themovieapps.ui.theme.semiBold
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    toHome: () -> Unit
) {
    val delayTime = 2000L
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.img_tmdb),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = modifier.width(150.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "The Movie Apps",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 20.sp,
                        fontWeight = semiBold
                    )
                )
            }
        }

        LaunchedEffect(Unit) {
            delay(delayTime)
            toHome.invoke()
        }
    }
}