plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

val compileSdkVer: Int by rootProject.extra
val targetSdkVer: Int by rootProject.extra
val minimumSdkVer: Int by rootProject.extra
val versionNameConfig: String by rootProject.extra
val versionCodeConfig: Int by rootProject.extra

android {
    namespace = "com.example.pagingtest"
    compileSdk = compileSdkVer

    defaultConfig {
        applicationId = "com.example.pagingtest"
        minSdk = minimumSdkVer
        targetSdk = targetSdkVer
        versionCode = versionCodeConfig
        versionName = versionNameConfig

        setProperty("archivesBaseName", "Paging Test-$versionNameConfig")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildFeatures {
            viewBinding = true
        }
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }

    kotlinOptions {
        jvmTarget = "19"
    }
}

dependencies {
    implementation(project(":base"))

    implementation(libs.androidx.constraintLayout)

    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.runtime)

    coreLibraryDesugaring(libs.desugar.jdk.libs)

    //material
    implementation(libs.material)
    //koin
    implementation(libs.di.koin.android)

    //timber
    implementation(libs.timber)

    //glide
    implementation(libs.glide)

    testImplementation(libs.junit.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}