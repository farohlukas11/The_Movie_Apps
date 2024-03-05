package com.dicoding.themovieapps.presentation.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.themovieapps.domain.model.BottomNavigationItem

@Composable
fun TheMovieBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)),
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        imageVector = bottomNavigationItem.icon,
                        contentDescription = bottomNavigationItem.text,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                },
                label = {
                    Text(
                        text = bottomNavigationItem.text, fontSize = 12.sp,
                    )
                }
            )
        }
    }
}