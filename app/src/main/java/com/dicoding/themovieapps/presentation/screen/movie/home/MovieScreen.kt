package com.dicoding.themovieapps.presentation.screen.movie.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.domain.model.MovieModel
import com.dicoding.themovieapps.presentation.screen.movie.home.viewmodel.MovieEvent
import com.dicoding.themovieapps.presentation.screen.movie.home.viewmodel.MovieState
import com.dicoding.themovieapps.ui.theme.bold
import com.dicoding.themovieapps.presentation.component.MovieHorizontalItem
import com.dicoding.themovieapps.presentation.component.MovieVerticalItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    movieState: MovieState,
    movieEvent: (MovieEvent) -> Unit,
    modifier: Modifier = Modifier,
    toDetail: (MovieModel) -> Unit,
    toSearch: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Movies Page",
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontSize = 18.sp,
                            fontWeight = bold
                        )
                    )
                },
                actions = {
                    IconButton(onClick = toSearch) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    }
                },
            )
        },
    ) { paddingValues ->
        val upcomingMovies =
            movieState.upcomingMovies?.collectAsStateWithLifecycle(initialValue = null)?.value
        val popularMovies =
            movieState.popularMovies?.collectAsStateWithLifecycle(initialValue = null)?.value
        val topRatedMovies =
            movieState.topRatedMovies?.collectAsStateWithLifecycle(initialValue = null)?.value

        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            upcomingMovies?.let { resource ->
                when (resource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val listUpcomingMovies = resource.data

                        if (!listUpcomingMovies.isNullOrEmpty()) {
                            UpcomingMoviesContent(
                                upcomingMovies = listUpcomingMovies,
                                toDetail = toDetail
                            )
                        }
                    }

                    is Resource.Error -> {

                    }
                }
            }
            popularMovies?.let { resource ->
                when (resource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val listPopularMovies = resource.data

                        if (!listPopularMovies.isNullOrEmpty()) {
                            PopularMoviesContent(
                                popularMovies = listPopularMovies,
                                toDetail = toDetail
                            )
                        }
                    }

                    is Resource.Error -> {

                    }
                }
            }

            topRatedMovies?.let { resource ->
                when (resource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val listTopRatedMovies = resource.data

                        if (!listTopRatedMovies.isNullOrEmpty()) {
                            TopRatedMoviesContent(
                                topRatedMovies = listTopRatedMovies,
                                toDetail = toDetail
                            )
                        }
                    }

                    is Resource.Error -> {

                    }
                }
            }
        }

        LaunchedEffect(movieState.message) {
            movieState.message?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            movieEvent(MovieEvent.OnRemoveMessageSideEffect)
        }
    }
}

@Composable
fun UpcomingMoviesContent(
    upcomingMovies: List<MovieModel>,
    modifier: Modifier = Modifier,
    toDetail: (MovieModel) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            text = "Upcoming Movies",
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 16.sp, fontWeight = bold),
            modifier = modifier.padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(
                upcomingMovies,
                key = { it.id }
            ) { movieModel ->
                MovieHorizontalItem(movieModel = movieModel, onClick = toDetail)
            }
        }
    }
}

@Composable
fun PopularMoviesContent(
    popularMovies: List<MovieModel>,
    modifier: Modifier = Modifier,
    toDetail: (MovieModel) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            text = "Popular Movies",
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 16.sp, fontWeight = bold),
            modifier = modifier.padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(
                popularMovies,
                key = { it.id }
            ) { movieModel ->
                MovieHorizontalItem(movieModel = movieModel, onClick = toDetail)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopRatedMoviesContent(
    topRatedMovies: List<MovieModel>,
    modifier: Modifier = Modifier,
    toDetail: (MovieModel) -> Unit
) {
    Column(modifier = modifier.padding(vertical = 10.dp, horizontal = 10.dp)) {
        Text(
            text = "Top Rated Movies",
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 16.sp, fontWeight = bold),
        )
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow {
            topRatedMovies.forEach { movieModel ->
                Box(
                    modifier = modifier.padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    MovieVerticalItem(movieModel = movieModel, onClick = toDetail)
                }
            }
        }
    }
}

