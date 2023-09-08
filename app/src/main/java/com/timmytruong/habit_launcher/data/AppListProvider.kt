package com.timmytruong.habit_launcher.data

import android.content.Intent
import android.content.pm.PackageManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppListProvider @Inject constructor(
    private val packageManager: PackageManager,
) {

    fun getApps(): List<AppInfo> {
        val intent = Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER)
        val apps = packageManager.queryIntentActivities(intent, 0).map {
            AppInfo(
                label = it.loadLabel(packageManager),
                packageName = it.activityInfo.packageName,
                icon = it.activityInfo.loadIcon(packageManager),
            )
        }
        return apps
    }
}