package com.timmytruong.habit_launcher.data

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import io.kotest.matchers.shouldBe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AppListProviderTest {

    private lateinit var packageManager: PackageManager
    private lateinit var subject: AppListProvider

    @Before
    fun setup() {
        packageManager = mock()
        subject = AppListProvider(packageManager)
    }

    @Test
    fun `Given packageManager, when getApps is called, then queryIntentActivities is called with correct intent`() {
        val captor = argumentCaptor<Intent>()
        subject.getApps()
        verify(packageManager).queryIntentActivities(captor.capture(), eq(0))
        captor.lastValue.categories.firstOrNull() shouldBe Intent.CATEGORY_LAUNCHER
        captor.lastValue.action shouldBe Intent.ACTION_MAIN
    }

    @Test
    fun `Given packageManager and resolveInfo, when getApps is called, AppInfos are returned`() {
        val mockDrawable = mock<Drawable>()
        val mockActivityInfo = mock<ActivityInfo> {
            on { loadIcon(any()) } doReturn mockDrawable
            on { packageName } doReturn "package name"
        }
        val mockResolveInfo = mock<ResolveInfo> {
            on { loadLabel(any()) } doReturn "app name"
            on { activityInfo } doReturn mockActivityInfo
        }
        whenever(packageManager.queryIntentActivities(any(), any<Int>())).thenReturn(listOf(mockResolveInfo))

        val result = subject.getApps()

        with(result.firstOrNull()!!) {
            label shouldBe "app name"
            packageName shouldBe "package name"
            icon shouldBe mockDrawable
        }
    }
}