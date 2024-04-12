package io.github.pawgli.kmpapptemplate.config

import io.github.pawgli.kmpapptemplate.util.getLibrary
import io.github.pawgli.kmpapptemplate.util.libs
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureSqlDelight(multiplatformExtension: KotlinMultiplatformExtension) {
  with(multiplatformExtension) {
    with(sourceSets) {
      commonMain.dependencies {
        implementation(libs.getLibrary("sqldelight-coroutines"))
      }
      androidMain.dependencies {
        implementation(libs.getLibrary("sqldelight-androidDriver"))
      }
      iosMain.dependencies {
        implementation(libs.getLibrary("sqldelight-nativeDriver"))
      }
    }
  }
}
