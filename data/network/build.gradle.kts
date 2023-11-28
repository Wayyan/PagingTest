plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

val compileSdkVer: Int by rootProject.extra
val minimumSdkVer: Int by rootProject.extra

android {
    namespace = "com.example.pagingtest.data.network"
    compileSdk = compileSdkVer

    defaultConfig {
        minSdk = minimumSdkVer

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false

            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://api.punkapi.com/v2/\""
            )
        }

        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://api.punkapi.com/v2/\""
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

    buildFeatures.buildConfig = true
}

dependencies {
    implementation(project(":data:common"))
    implementation(project(":domain"))

    coreLibraryDesugaring(libs.desugar.jdk.libs)

    //androidx
    implementation(libs.androidx.core)

    //coroutine
    implementation(libs.coroutine.android)

    //networking
    implementation(libs.okhttp.client)
    implementation(libs.okhttp.logger)
    testImplementation(libs.okhttp.mockWebServer)
    implementation(libs.retrofit.main)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.moshi.core)
    implementation(libs.moshi.adapters)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.codeGen)

    //koin
    implementation(libs.di.koin.android)

    //timber
    implementation(libs.timber)

    testImplementation(libs.junit.junit4)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}