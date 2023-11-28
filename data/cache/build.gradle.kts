@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

val compileSdkVer: Int by rootProject.extra
val minimumSdkVer: Int by rootProject.extra

android {
    namespace = "com.example.pagingtest.data.cache"
    compileSdk = compileSdkVer

    defaultConfig {
        minSdk = minimumSdkVer

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
}

dependencies {
    implementation(project(":data:common"))
    implementation(project(":domain"))

    implementation(libs.androidx.core)

    //room
    implementation(libs.bundles.room)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)
    testImplementation(libs.room.testing)

    //koin
    implementation(libs.di.koin.android)

    //timber
    implementation(libs.timber)

    testImplementation(libs.junit.junit4)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso)
    testImplementation(libs.bundles.androidx.test)
    androidTestImplementation(libs.bundles.androidx.test)
}