plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.timmytruong.simplelauncher"

    compileSdk = Config.SdkVersions.COMPILE

    defaultConfig {
        applicationId = Config.APP_ID
        minSdk = Config.SdkVersions.MIN
        targetSdk = Config.SdkVersions.TARGET
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        compileSdkPreview = "UpsideDownCake"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libraries.Compose.COMPILER_VERSION
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // AndroidX
    implementation(Libraries.AndroidX.CORE_KTX)
    implementation(Libraries.AndroidX.LIFECYCLE_KTX)
    implementation(Libraries.AndroidX.VIEWMODEL)

    // Compose
    implementation(platform(Libraries.Compose.BOM))
    implementation(Libraries.Compose.ACTIVITY)
    implementation(Libraries.Compose.VIEWMODEL)
    implementation(Libraries.Compose.MATERIAL3)
    implementation(Libraries.Compose.UI)

    // Accompanist
    implementation(Libraries.Accompanist.DRAWABLE_PAINTER)

    // Hilt
    implementation(Libraries.Hilt.HILT)
    kapt(Libraries.Hilt.COMPILER)

    // Coroutines
    implementation(Libraries.Coroutines.CORE)
    implementation(Libraries.Coroutines.ANDROID)

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // Unit Testing
    testImplementation("org.mockito:mockito-core:5.0.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.0.0")
    testImplementation("org.robolectric:robolectric:4.9")

    // KoTest Assertions
    testImplementation("io.kotest:kotest-assertions-core:5.4.2")

    // Ui Testing
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}