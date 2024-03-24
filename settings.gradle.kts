pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}


dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        // Define any repositories that are common to all projects here
        google()
        mavenCentral()
    }
}

rootProject.name = "PHR"
include(":app")
