package com.timmytruong.simplelauncher.ui.main.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.timmytruong.simplelauncher.data.AppInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun AppItem(
    modifier: Modifier = Modifier,
    appInfo: AppInfo,
    onClick: (AppInfo) -> Unit,
    onLongClick: (AppInfo) -> Unit
) {
    val color = MaterialTheme.colorScheme.primary

    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onClick(appInfo) },
                onLongClick = { onLongClick(appInfo) }
            ),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Canvas(modifier = Modifier.size(12.dp)) { drawCircle(color = color) }
            AppIcon(drawable = appInfo.icon, name = appInfo.label)
            Text(text = appInfo.label.toString(), color = color)
        }
    )
}