package com.timmytruong.simplelauncher.data

sealed class Event {
    data class NavigateToPackage(val packageName: CharSequence): Event()
}
