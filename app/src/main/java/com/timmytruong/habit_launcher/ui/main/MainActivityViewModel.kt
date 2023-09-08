package com.timmytruong.habit_launcher.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timmytruong.habit_launcher.data.AppInfo
import com.timmytruong.habit_launcher.data.AppListProvider
import com.timmytruong.habit_launcher.data.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val appListProvider: AppListProvider,
): ViewModel() {

    private val _apps = MutableStateFlow<List<AppInfo>>(emptyList())
    val apps: StateFlow<List<AppInfo>> = _apps

    private  val _eventsFlow = MutableSharedFlow<Event>()
    val eventsFlow: Flow<Event> = _eventsFlow

    fun fetchApps() {
        viewModelScope.launch {
            val appData = appListProvider.getApps()
            val sortedAppData = appData.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.label.toString() })
            _apps.value = sortedAppData
        }
    }

    fun onAppPressed(appInfo: AppInfo) {
        viewModelScope.launch {
            _eventsFlow.emit(Event.NavigateToPackage(packageName = appInfo.packageName))
        }
    }
}