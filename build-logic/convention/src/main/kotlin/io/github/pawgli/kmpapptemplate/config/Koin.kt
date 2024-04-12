package io.github.pawgli.kmpapptemplate.config

import io.github.pawgli.kmpapptemplate.util.getLibrary
import io.github.pawgli.kmpapptemplate.util.libs
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKoin(multiplatformExtension: KotlinMultiplatformExtension) {
  with(multiplatformExtension) {
    with(sourceSets) {
      val koinBom: Provider<MinimalExternalModuleDependency> =
        dependencies.platform(libs.getLibrary("koin-bom"))

      commonMain.dependencies {
        implementation(koinBom)
        implementation(libs.getLibrary("koin-core"))
      }
      commonTest.dependencies {
        implementation(koinBom)
        implementation(libs.getLibrary("koin-test"))
      }
      androidMain.dependencies {
        implementation(koinBom)
        implementation(libs.getLibrary("koin-android"))
      }
    }
  }
}
