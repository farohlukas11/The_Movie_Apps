package com.dicoding.themovieapps.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dicoding.themovieapps.data.utils.BASE_IMAGE_URL
import com.dicoding.themovieapps.domain.model.TvModel
import com.dicoding.themovieapps.ui.theme.medium

@Composable
fun TvHorizontalItem(
    tvModel: TvModel,
    onClick: (TvModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val tvVoteAverage = tvModel.voteAverage ?: 0.0
    val posterPathUrl = "$BASE_IMAGE_URL${tvModel.posterPath}"

    Column(
        modifier = modifier
            .width(143.dp)
            .clickable {
                onClick(tvModel)
            },
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context).data(posterPathUrl).build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .height(212.dp)
                .clip(RoundedCornerShape(8))
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = tvModel.name ?: "",
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = medium,
                fontSize = 15.sp
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(5.dp))
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
            Spacer(modifier = Modifier.width(1.5.dp))
            Text(
                text = "$tvVoteAverage/10 IMDb",
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 12.sp)
            )
        }
    }
}