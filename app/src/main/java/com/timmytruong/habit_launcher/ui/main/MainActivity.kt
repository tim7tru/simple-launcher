package com.timmytruong.habit_launcher.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.timmytruong.habit_launcher.data.BottomSheetState
import com.timmytruong.habit_launcher.data.Event
import com.timmytruong.habit_launcher.ui.main.composable.AppList
import com.timmytruong.habit_launcher.ui.menu.composable.MenuBottomSheet
import com.timmytruong.habit_launcher.ui.theme.MainActivityTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityTheme {
                val bottomSheetState by viewModel.bottomSheetState.collectAsState()

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .combinedClickable(
                            onClick = {}, onLongClick = viewModel::onBackgroundLongClick
                        ),
                    color = Color.Transparent
                ) {
                    AppList(viewModel = viewModel)
                    if (bottomSheetState.isVisible) {
                        MenuBottomSheet(
                            onDismissRequest = viewModel::closeBottomSheet,
                            appInfo = (bottomSheetState as? BottomSheetState.OpenedOnApp)?.appInfo,
                        )
                    }
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