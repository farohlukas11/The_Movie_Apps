package com.dicoding.themovieapps.presentation.screen.movie.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
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

@Composable
fun MovieDetailScreen(
    movieDetailState: MovieDetailState,
    movieDetailEvent: (MovieDetailEvent) -> Unit,
    modifier: Modifier = Modifier,
    movieModel: MovieModel,
    toDetail: (MovieModel) -> Unit
) {
    val context = LocalContext.current
    val backdropPathUrl = "$BASE_IMAGE_URL${movieModel.backdropPath}"
    val posterPathUrl = "$BASE_IMAGE_URL${movieModel.posterPath}"

    movieDetailEvent(MovieDetailEvent.OnInitRecommendationsMovie(movieModel.id))

    val movieRecommendations = movieDetailState.movieRecommendation?.collectAsStateWithLifecycle(
        initialValue = null
    )?.value

    Box {
        AsyncImage(
            model = ImageRequest.Builder(context).data(backdropPathUrl).build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .height(400.dp)
        )
        Box(modifier = modifier.padding(top = 200.dp)) {
            Box(modifier = modifier.padding(top = 159.dp)) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                        )
                        .padding(top = 60.dp)
                ) {
                    Text(
                        text = movieModel.title ?: "",
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontWeight = medium,
                            fontSize = 20.sp
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
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
            }
            AsyncImage(
                model = ImageRequest.Builder(context).data(posterPathUrl).build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(start = 20.dp)
                    .width(143.dp)
                    .height(212.dp)
                    .clip(RoundedCornerShape(8))
            )
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
            modifier = modifier.padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp),
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