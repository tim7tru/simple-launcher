package com.timmytruong.habit_launcher.data

sealed class BottomSheetState {
    abstract val isVisible: Boolean

    data class OpenedOnApp(val appInfo: AppInfo): BottomSheetState() {
        override val isVisible: Boolean = true
    }

    data object OpenedOnBackground: BottomSheetState() {
        override val isVisible: Boolean = true
    }

    data object Closed: BottomSheetState() {
        override val isVisible: Boolean = false
    }
}
