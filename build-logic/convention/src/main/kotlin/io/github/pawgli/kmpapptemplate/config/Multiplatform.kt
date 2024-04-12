package io.github.pawgli.kmpapptemplate.config

import io.github.pawgli.kmpapptemplate.extension.SupportedTargets
import io.github.pawgli.kmpapptemplate.extension.SupportedTargetsImpl
import io.github.pawgli.kmpapptemplate.util.getLibrary
import io.github.pawgli.kmpapptemplate.util.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.add
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform(
  multiplatformExtension: KotlinMultiplatformExtension
) {
  with(multiplatformExtension) {
    addSupportedTargetsExtension(multiplatformExtension = this)

    with(sourceSets) {
      commonMain.dependencies {
        implementation(libs.getLibrary("kotlinx-coroutines-core"))
        implementation(libs.getLibrary("kotlinx-datetime"))
      }
      commonTest.dependencies {
        implementation(libs.getLibrary("kotlin-test"))
        implementation(libs.getLibrary("kotest-assertions"))
        implementation(libs.getLibrary("turbine"))
        implementation(libs.getLibrary("kotlinx-coroutines-test"))
      }
      androidMain.dependencies {
        implementation(libs.getLibrary("kotlinx-coroutines-android"))
      }
    }
  }
}

private fun Project.addSupportedTargetsExtension(
  multiplatformExtension: KotlinMultiplatformExtension
) = with(multiplatformExtension) {
  val androidTargets = listOf(
    androidTarget()
  )
  val iosTargets = listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64(),
  )

  extensions.add(
    name = "supportedTargets",
    publicType = SupportedTargets::class,
    extension = SupportedTargetsImpl(androidTargets, iosTargets),
  )
}
