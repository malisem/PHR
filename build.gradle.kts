buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2") // Make sure this is the right version for your project
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10") // Kotlin Gradle Plugin
        classpath("com.google.gms:google-services:4.4.1")
        classpath("com.google.devtools.ksp:symbol-processing-gradle-plugin:1.8.10-1.0.9")

    }
}

plugins {
    id("com.android.application") version "8.2.2" apply false // Updated version
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// Corrected task declaration for 'clean'
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

