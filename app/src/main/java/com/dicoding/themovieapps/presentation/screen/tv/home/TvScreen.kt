package com.dicoding.themovieapps.presentation.screen.tv.home

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
import com.dicoding.themovieapps.domain.model.TvModel
import com.dicoding.themovieapps.presentation.screen.tv.home.viewmodel.TvEvent
import com.dicoding.themovieapps.presentation.screen.tv.home.viewmodel.TvState
import com.dicoding.themovieapps.ui.theme.bold
import com.dicoding.themovieapps.presentation.component.TvHorizontalItem
import com.dicoding.themovieapps.presentation.component.TvVerticalItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvScreen(
    tvState: TvState,
    tvEvent: (TvEvent) -> Unit,
    modifier: Modifier = Modifier,
    toDetail: (TvModel) -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Tv Page",
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
        val onTheAirTv = tvState.onTheAirTv?.collectAsStateWithLifecycle(initialValue = null)?.value
        val popularTv = tvState.popularTv?.collectAsStateWithLifecycle(initialValue = null)?.value
        val topRatedTv = tvState.topRatedTv?.collectAsStateWithLifecycle(initialValue = null)?.value

        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            onTheAirTv?.let { resource ->
                when (resource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val listOnTeAirTv = resource.data

                        if (!listOnTeAirTv.isNullOrEmpty()) {
                            OnTheAirTvContent(onTheAirTv = listOnTeAirTv, toDetail = toDetail)
                        }
                    }

                    is Resource.Error -> {

                    }
                }
            }

            popularTv?.let { resource ->
                when (resource) {
                    is Resource.Loading -> {}

                    is Resource.Success -> {
                        val listPopularTv = resource.data

                        if (!listPopularTv.isNullOrEmpty()) {
                            PopularTvContent(popularTv = listPopularTv) {

                            }
                        }
                    }

                    is Resource.Error -> {

                    }
                }
            }

            topRatedTv?.let { resource ->
                when (resource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val listTopRatedTv = resource.data

                        if (!listTopRatedTv.isNullOrEmpty()) {
                            TopRatedTvContent(topRatedTv = listTopRatedTv) {

                            }
                        }
                    }

                    is Resource.Error -> {

                    }
                }
            }
        }

        LaunchedEffect(tvState.message) {
            tvState.message?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            tvEvent(TvEvent.OnRemoveMessageSideEffect)
        }
    }
}

@Composable
fun OnTheAirTvContent(
    onTheAirTv: List<TvModel>,
    modifier: Modifier = Modifier,
    toDetail: (TvModel) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            text = "On The Air Tv",
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 16.sp,
                fontWeight = bold
            ),
            modifier = modifier.padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(
                onTheAirTv,
                key = { it.id }
            ) { tvModel ->
                TvHorizontalItem(tvModel = tvModel, onClick = toDetail)
            }
        }
    }
}

@Composable
fun PopularTvContent(
    popularTv: List<TvModel>,
    modifier: Modifier = Modifier,
    toDetail: (TvModel) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            text = "Popular Tv",
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 16.sp,
                fontWeight = bold
            ),
            modifier = modifier.padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(
                popularTv,
                key = { it.id }
            ) { tvModel ->
                TvHorizontalItem(tvModel = tvModel, onClick = toDetail)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopRatedTvContent(
    topRatedTv: List<TvModel>,
    modifier: Modifier = Modifier,
    toDetail: (TvModel) -> Unit
) {
    Column(modifier = modifier.padding(vertical = 10.dp, horizontal = 10.dp)) {
        Text(
            text = "Top Rated Tv",
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 16.sp, fontWeight = bold),
        )
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow {
            topRatedTv.forEach { tvModel ->
                Box(
                    modifier = modifier.padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    TvVerticalItem(tvModel = tvModel, onClick = toDetail)
                }
            }
        }
    }
}