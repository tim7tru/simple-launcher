package com.timmytruong.habit_launcher.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.timmytruong.habit_launcher.data.AppInfo
import com.timmytruong.habit_launcher.data.Event
import com.timmytruong.habit_launcher.ui.theme.MainActivityTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent
                ) {
                    AppList(viewModel = viewModel)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchApps()
        lifecycleScope.launch {
            viewModel.eventsFlow.collect(::handleEvent)
        }
    }

    @Composable
    private fun AppList(viewModel: MainActivityViewModel) {
        val apps = viewModel.apps.collectAsState()

        return LazyColumn(
            contentPadding = PaddingValues(all = 16.dp)
        ) {
            items(apps.value) {
                AppItem(it, viewModel::onAppPressed)
            }
        }
    }

    @Composable
    private fun AppItem(appInfo: AppInfo, onClick: (AppInfo) -> Unit) {
        val color = MaterialTheme.colorScheme.primary
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { onClick(appInfo) },
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Canvas(modifier = Modifier.size(12.dp)) { drawCircle(color = color) }
                Image(imageVector = , contentDescription = )
                Text(text = appInfo.label.toString(), color = MaterialTheme.colorScheme.primary)
            }
        )
    }

    private fun handleEvent(event: Event) {
        when (event) {
            is Event.NavigateToPackage -> openPackage(event.packageName)
        }
    }

    private fun openPackage(packageName: CharSequence) {
        baseContext?.run {
            startActivity(packageManager.getLaunchIntentForPackage(packageName.toString()))
        }
    }
}