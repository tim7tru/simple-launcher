package com.timmytruong.habit_launcher.data

sealed class Event {
    data class NavigateToPackage(val packageName: CharSequence): Event()
}
