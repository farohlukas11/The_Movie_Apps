package com.dicoding.themovieapps.presentation.screen.movie.search

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dicoding.themovieapps.data.source.Resource
import com.dicoding.themovieapps.domain.model.MovieModel
import com.dicoding.themovieapps.presentation.component.MovieVerticalItem
import com.dicoding.themovieapps.presentation.screen.movie.search.viewmodel.MovieSearchEvent
import com.dicoding.themovieapps.presentation.screen.movie.search.viewmodel.MovieSearchState
import com.dicoding.themovieapps.ui.theme.medium

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieSearchScreen(
    movieSearchState: MovieSearchState,
    movieSearchEvent: (MovieSearchEvent) -> Unit,
    modifier: Modifier = Modifier,
    toDetail: (MovieModel) -> Unit
) {
    val context = LocalContext.current
    val searchMovies =
        movieSearchState.searchMovies?.collectAsStateWithLifecycle(initialValue = null)?.value

    Scaffold(
        topBar = {
            TextField(
                value = movieSearchState.searchText,
                onValueChange = { query ->
                    movieSearchEvent(MovieSearchEvent.OnQueryChange(query))
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                textStyle = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = medium,
                    fontSize = 16.sp
                ),
                placeholder = {
                    Text(
                        text = "Masukkan Judul Movie...",
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = medium,
                            fontSize = 16.sp
                        ),
                    )
                },
                shape = RoundedCornerShape(10.dp),
                maxLines = 1,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                }
            )
        }
    ) { paddingValues ->
        FlowRow(modifier = modifier.padding(paddingValues)) {
            searchMovies?.let { resource ->
                when (resource) {
                    is Resource.Loading -> Box(
                        contentAlignment = Alignment.Center,
                        modifier = modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }

                    is Resource.Success -> {
                        val listSearchMovie = resource.data

                        if (!listSearchMovie.isNullOrEmpty()) LazyColumn {
                            items(
                                listSearchMovie,
                                key = { it.id }
                            ) { movieModel ->
                                Box(
                                    modifier = modifier.padding(
                                        vertical = 4.dp,
                                        horizontal = 5.dp
                                    ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    MovieVerticalItem(
                                        movieModel = movieModel,
                                        onClick = toDetail
                                    )
                                }
                            }
                        } else Box(
                            contentAlignment = Alignment.Center,
                            modifier = modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "Movie Tidak Ditemukan!",
                                style = MaterialTheme.typography.displayLarge.copy(fontSize = 16.sp)
                            )
                        }
                    }

                    is Resource.Error -> movieSearchEvent(MovieSearchEvent.OnInitMessage(resource.message))
                }
            }
        }

        LaunchedEffect(movieSearchState) {
            movieSearchState.message?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            movieSearchEvent(MovieSearchEvent.OnRemoveMessageSideEffect)
        }
    }
}