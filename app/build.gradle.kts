plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.project.phr"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.project.phr"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:2.6.1")
    //for view model property delegate
    implementation ("androidx.fragment:fragment-ktx:1.6.2")
    implementation ("androidx.activity:activity-ktx:1.8.2")

    //for coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    //for firebase coroutine support
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

    //for flow as live data
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Firebase Firestore Database
    implementation ("com.google.firebase:firebase-firestore-ktx")
    // Firebase Auth
    implementation ("com.google.firebase:firebase-auth-ktx")

    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation ("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.ai.client.generativeai:generativeai:0.2.1")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}