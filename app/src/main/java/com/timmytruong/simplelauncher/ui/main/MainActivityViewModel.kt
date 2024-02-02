package com.timmytruong.simplelauncher.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timmytruong.simplelauncher.data.AppInfo
import com.timmytruong.simplelauncher.data.AppListProvider
import com.timmytruong.simplelauncher.data.BottomSheetState
import com.timmytruong.simplelauncher.data.Event
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

    private val _bottomSheetState = MutableStateFlow<BottomSheetState>(BottomSheetState.Closed)
    val bottomSheetState: StateFlow<BottomSheetState> = _bottomSheetState

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

    fun onAppLongPressed(appInfo: AppInfo) {
        viewModelScope.launch {
            _bottomSheetState.emit(BottomSheetState.OpenedOnApp(appInfo = appInfo))
        }
    }

    fun closeBottomSheet() {
        viewModelScope.launch {
            _bottomSheetState.emit(BottomSheetState.Closed)
        }
    }

    fun onBackgroundLongClick() {
        viewModelScope.launch {
            _bottomSheetState.emit(BottomSheetState.OpenedOnBackground)
        }
    }
}