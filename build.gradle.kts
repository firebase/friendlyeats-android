// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.android.application") version "8.1.4" apply false
    id("com.android.library") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("androidx.navigation.safeargs") version "2.7.5" apply false
    id("com.github.ben-manes.versions") version "0.50.0" apply true
}

allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

fun isNonStable(candidate: ModuleComponentIdentifier): Boolean {
    return listOf("alpha", "beta", "rc", "snapshot").any { keyword ->
        keyword in candidate.version.lowercase()
    }
}

fun isBlockListed(candidate: ModuleComponentIdentifier): Boolean {
    return listOf("androidx.browser:browser").any { keyword ->
        keyword in candidate.toString().lowercase()
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate) || isBlockListed(candidate)
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}
