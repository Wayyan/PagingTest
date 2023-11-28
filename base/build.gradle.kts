plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

val compileSdkVer: Int by rootProject.extra
val minimumSdkVer: Int by rootProject.extra

android {
    namespace = "com.example.pagingtest.base"
    compileSdk = compileSdkVer

    defaultConfig {
        minSdk = minimumSdkVer

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildFeatures {
            viewBinding = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    api(project(":domain"))
    api(project(":data:common"))
    api(project(":data:network"))
    api(project(":data:cache"))
    api(project(":data:paging"))

    coreLibraryDesugaring(libs.desugar.jdk.libs)

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recyclerview)

    //material
    implementation(libs.material)

    //koin
    implementation(libs.di.koin.android)

    //timber
    implementation(libs.timber)

    testImplementation(libs.junit.junit4)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}