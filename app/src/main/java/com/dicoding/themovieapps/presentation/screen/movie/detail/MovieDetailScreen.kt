package com.dicoding.themovieapps.presentation.screen.movie.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.data.utils.BASE_IMAGE_URL
import com.dicoding.themovieapps.domain.model.MovieModel
import com.dicoding.themovieapps.presentation.component.MovieHorizontalItem
import com.dicoding.themovieapps.presentation.screen.movie.detail.viewmodel.MovieDetailEvent
import com.dicoding.themovieapps.presentation.screen.movie.detail.viewmodel.MovieDetailState
import com.dicoding.themovieapps.ui.theme.bold
import com.dicoding.themovieapps.ui.theme.medium
import com.dicoding.themovieapps.ui.theme.whiteColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieDetailState: MovieDetailState,
    movieDetailEvent: (MovieDetailEvent) -> Unit,
    modifier: Modifier = Modifier,
    movieModel: MovieModel,
    toDetail: (MovieModel) -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val backdropPathUrl = "$BASE_IMAGE_URL${movieModel.backdropPath}"
    val posterPathUrl = "$BASE_IMAGE_URL${movieModel.posterPath}"

    LaunchedEffect(Unit) {
        movieDetailEvent(MovieDetailEvent.OnInitRecommendationsMovie(movieModel.id))
    }

    val movieRecommendations = movieDetailState.movieRecommendation?.collectAsStateWithLifecycle(
        initialValue = null
    )?.value

    BottomSheetScaffold(
        sheetContent = {
            Column(
                modifier = modifier
                    .fillMaxHeight(0.8f)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(modifier = modifier.padding(horizontal = 20.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context).data(posterPathUrl).build(),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                                .width(143.dp)
                                .height(212.dp)
                                .clip(RoundedCornerShape(8))
                        )
                        Column {
                            IconButton(
                                onClick = { },
                                modifier = modifier.size(36.dp)
                            ) {
                                Icon(
                                    imageVector = if (movieModel.isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                    Text(
                        text = movieModel.title ?: "",
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontWeight = medium,
                            fontSize = 20.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = movieModel.overview ?: "",
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontSize = 14.sp,
                            fontWeight = medium
                        ),
                        textAlign = TextAlign.Justify
                    )
                }
                movieRecommendations?.let { resource ->
                    when (resource) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            val listRecommendationMovie = resource.data

                            if (!listRecommendationMovie.isNullOrEmpty()) {
                                RecommendationMoviesContent(
                                    recommendationsMovie = listRecommendationMovie,
                                    toDetail = toDetail
                                )
                            }
                        }

                        is Resource.Error -> {

                        }
                    }
                }
            }
        },
        sheetPeekHeight = 270.dp,
        sheetSwipeEnabled = true
    ) { paddingValues ->
        Box(contentAlignment = Alignment.TopStart) {
            AsyncImage(
                model = ImageRequest.Builder(context).data(backdropPathUrl).build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxSize()
            )
            IconButton(
                onClick = { onBack() },
                modifier = modifier
                    .padding(
                        top = 10.dp,
                        start = 10.dp,
                        end = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateTopPadding()
                    )
                    .background(color = Color.DarkGray, shape = CircleShape)
                    .size(36.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "",
                    tint = whiteColor
                )
            }
        }
    }
}

@Composable
fun RecommendationMoviesContent(
    recommendationsMovie: List<MovieModel>,
    modifier: Modifier = Modifier,
    toDetail: (MovieModel) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            text = "Recommendations Movies",
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 16.sp, fontWeight = bold),
            modifier = modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 20.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(
                recommendationsMovie,
                key = { it.id }
            ) { movieModel ->
                MovieHorizontalItem(movieModel = movieModel, onClick = toDetail)
            }
        }
    }
}