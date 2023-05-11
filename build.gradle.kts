// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.android.application") version "8.0.0" apply false
    id("com.android.library") version "8.0.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
    id("androidx.navigation.safeargs") version "2.5.3" apply false
    id("com.github.ben-manes.versions") version "0.41.0" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

fun isNonStable(candidate: ModuleComponentIdentifier): Boolean {
    return listOf("alpha", "beta", "rc", "SNAPSHOT").any { keyword ->
        candidate.version.contains(keyword)
    }
}

fun isBlackListed(candidate: ModuleComponentIdentifier): Boolean {
    return listOf("androidx.browser:browser").any { keyword ->
        candidate.toString().contains(keyword)
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate) || isBlackListed(candidate)
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}
