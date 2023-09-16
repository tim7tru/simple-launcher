object Libraries {

    object AndroidX {
        private const val CORE_KTX_VERSION = "1.12.0"
        private const val LIFECYCLE_VERSION = "2.6.2"
        const val CORE_KTX = "androidx.core:core-ktx:$CORE_KTX_VERSION"
        const val LIFECYCLE_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_VERSION"
        const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION"
    }

    object Compose {
        private const val VIEWMODEL_VERSION = "2.6.2"
        private const val ACTIVITY_VERSION = "1.7.2"
        private const val BOM_VERSION = "2023.06.01"
        const val COMPILER_VERSION = "1.5.2"

        const val BOM = "androidx.compose:compose-bom:$BOM_VERSION"
        const val ACTIVITY = "androidx.activity:activity-compose:$ACTIVITY_VERSION"
        const val UI = "androidx.compose.ui:ui"
        const val MATERIAL3 = "androidx.compose.material3:material3"
        const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:$VIEWMODEL_VERSION"
    }

    object Accompanist {
        private const val VERSION = "0.33.1-alpha"
        const val DRAWABLE_PAINTER = "com.google.accompanist:accompanist-drawablepainter:$VERSION"
    }

    object Hilt {
        private const val VERSION = "2.47"
        const val HILT = "com.google.dagger:hilt-android:$VERSION"
        const val COMPILER = "com.google.dagger:hilt-android-compiler:$VERSION"
    }

    object Coroutines {
        private const val VERSION = "1.7.1"
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
    }
}