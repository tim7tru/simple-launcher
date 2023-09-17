package com.timmytruong.habit_launcher.ui.menu.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.timmytruong.habit_launcher.R
import com.timmytruong.habit_launcher.data.AppInfo

private val iconSize = 36.dp

@Composable
fun MenuHeader(appInfo: AppInfo? = null, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (appInfo != null) {
            AppMenuHeaderIcon(appInfo = appInfo)
            MenuHeaderText(label = appInfo.label.toString())
        } else {
            DefaultMenuHeaderIcon()
            MenuHeaderText()
        }
    }
}

@Composable
private fun AppMenuHeaderIcon(appInfo: AppInfo, modifier: Modifier = Modifier) {
    Image(
        painter = rememberDrawablePainter(drawable = appInfo.icon),
        contentDescription = "${appInfo.label} ${stringResource(id = R.string.app_icon)}",
        modifier = modifier.size(iconSize),
    )
}

@Composable
private fun DefaultMenuHeaderIcon(modifier: Modifier = Modifier) {
    Icon(
        imageVector = Icons.Default.Settings, 
        contentDescription = stringResource(R.string.settings_icon),
        modifier = modifier.size(iconSize).padding(start = 16.dp),
    )
}

@Composable
private fun MenuHeaderText(label: String? = null, modifier: Modifier = Modifier) {
    Text(
        text = label ?: stringResource(id = R.string.app_name),
        modifier = modifier.padding(start = 16.dp),
        fontSize = 28.sp,
        fontWeight = FontWeight.Medium,
    )
}
