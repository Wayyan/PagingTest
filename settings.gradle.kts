pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Paging Test"
include(":app")
include(":base")
include(":domain")
include(":data:network")
include(":data:common")
include(":data:cache")
include(":data:paging")
