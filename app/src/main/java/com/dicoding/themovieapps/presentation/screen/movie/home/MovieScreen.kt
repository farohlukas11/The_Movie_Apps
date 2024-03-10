package com.dicoding.themovieapps.presentation.screen.movie.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.domain.model.MovieModel
import com.dicoding.themovieapps.presentation.screen.movie.home.viewmodel.MovieEvent
import com.dicoding.themovieapps.presentation.screen.movie.home.viewmodel.MovieState
import com.dicoding.themovieapps.ui.theme.bold
import com.dicoding.themovieapps.ui.theme.medium
import com.dicoding.themovieapps.ui.theme.semiBold
import com.dicoding.themovieapps.utils.BASE_IMAGE_URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    movieState: MovieState,
    movieEvent: (MovieEvent) -> Unit,
    modifier: Modifier = Modifier
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
                    IconButton(onClick = { /*TODO*/ }) {
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
                            UpcomingMoviesContent(upcomingMovies = listUpcomingMovies) {

                            }
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
                            PopularMoviesContent(popularMovies = listPopularMovies) {

                            }
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
                            TopRatedMoviesContent(topRatedMovies = listTopRatedMovies) {

                            }
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

@Composable
fun MovieHorizontalItem(
    movieModel: MovieModel,
    onClick: (MovieModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val movieVoteAverage = movieModel.voteAverage ?: 0.0
    val posterPathUrl = "$BASE_IMAGE_URL${movieModel.posterPath}"

    Column(
        modifier = modifier
            .width(143.dp)
            .clickable {
                onClick(movieModel)
            },
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context).data(posterPathUrl).build(),
            contentDescription = "",
            imageLoader = ImageLoader(context),
            contentScale = ContentScale.Fit,
            modifier = modifier
                .fillMaxWidth()
                .height(212.dp)
                .clip(RoundedCornerShape(8))
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = movieModel.title ?: "",
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = medium,
                fontSize = 15.sp
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "",
                tint = Color.Yellow,
                modifier = modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(1.5.dp))
            Text(
                text = "$movieVoteAverage/10 IMDb",
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 12.sp)
            )
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

@Composable
fun MovieVerticalItem(
    movieModel: MovieModel,
    onClick: (MovieModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val movieVoteAverage = movieModel.voteAverage ?: 0.0
    val posterPathUrl = "$BASE_IMAGE_URL${movieModel.posterPath}"

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(movieModel)
            }) {
        AsyncImage(
            model = ImageRequest.Builder(context).data(posterPathUrl).build(),
            contentDescription = "",
            imageLoader = ImageLoader(context),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .width(85.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(8))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = modifier
                .fillMaxHeight()
                .width(250.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = movieModel.title ?: "",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = medium,
                    fontSize = 15.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    tint = Color.Yellow,
                    modifier = modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$movieVoteAverage/10 IMDb",
                    style = MaterialTheme.typography.displayMedium.copy(fontSize = 12.sp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Language,
                    contentDescription = "",
                    modifier = modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = movieModel.originalLanguage ?: "",
                    style = MaterialTheme.typography.displayMedium.copy(fontSize = 12.sp)
                )
            }
        }
    }
}

