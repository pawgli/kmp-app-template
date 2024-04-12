package io.github.pawgli.kmpapptemplate.config

import io.github.pawgli.kmpapptemplate.util.getLibrary
import io.github.pawgli.kmpapptemplate.util.libs
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKtor(multiplatformExtension: KotlinMultiplatformExtension) {
  with(multiplatformExtension) {
    with(sourceSets) {
      commonMain.dependencies {
        implementation(libs.getLibrary("ktor-core"))
        implementation(libs.getLibrary("ktor-logging"))
        implementation(libs.getLibrary("ktor-contentNegotiation"))
        implementation(libs.getLibrary("ktor-serialization"))
        implementation(libs.getLibrary("kotlinx-serialization"))
        implementation(libs.getLibrary("kermit"))
      }
      androidMain.dependencies {
        implementation(libs.getLibrary("ktor-okhttp"))
      }
      iosMain.dependencies {
        implementation(libs.getLibrary("ktor-darwin"))
      }
    }
  }
}
