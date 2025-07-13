plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.productbrowser"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.productbrowser"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    // gson converter
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.moshi)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Saved state module for ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    kapt(libs.androidx.lifecycle.compiler.ktx)

    // Room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)

    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.activity.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //Glide
    implementation(libs.glide)
    kapt(libs.compiler)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}