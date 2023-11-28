plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
}

kotlin{
    jvmToolchain(19)
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.di.koin.core)
    
    implementation(libs.androidx.paging.common)

    testImplementation(libs.di.koin.test)
    testImplementation(libs.junit.junit4)
}