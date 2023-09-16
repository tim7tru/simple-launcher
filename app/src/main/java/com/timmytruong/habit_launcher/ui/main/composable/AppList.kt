package com.timmytruong.habit_launcher.ui.main.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.timmytruong.habit_launcher.ui.main.MainActivityViewModel

@Composable
internal fun AppList(
    modifier: Modifier = Modifier,
    viewModel: MainActivityViewModel
) {
    val apps = viewModel.apps.collectAsState()

    return LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(all = 16.dp)
    ) {
        items(apps.value) {
            AppItem(
                appInfo = it,
                onClick = viewModel::onAppPressed,
                onLongClick = viewModel::onAppLongPressed
            )
        }
    }
}