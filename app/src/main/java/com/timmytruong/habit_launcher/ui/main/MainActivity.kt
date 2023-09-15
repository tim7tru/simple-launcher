package com.timmytruong.habit_launcher.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.timmytruong.habit_launcher.data.Event
import com.timmytruong.habit_launcher.ui.main.composable.AppList
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