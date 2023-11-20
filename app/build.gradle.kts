plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.google.firebase.example.fireeats"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.google.firebase.example.fireeats"
        minSdk = 19
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }
    lint {
        // TODO(thatfiredev): Remove this once
        //  https://github.com/bumptech/glide/issues/4940 is fixed
        disable.add("NotificationPermission")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Import the Firebase BoM (see: https://firebase.google.com/docs/android/learn-more#bom)
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))

    // Firestore
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Other Firebase/Play services deps
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // FirebaseUI (for authentication)
    implementation("com.firebaseui:firebase-ui-auth:8.0.2")

    // Support Libs
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.vectordrawable:vectordrawable-animated:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.browser:browser:1.5.0")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    implementation("androidx.startup:startup-runtime:1.1.1")

    // Android architecture components
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:2.6.2")

    // Third-party libraries
    implementation("me.zhanghai.android.materialratingbar:library:1.4.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
}
