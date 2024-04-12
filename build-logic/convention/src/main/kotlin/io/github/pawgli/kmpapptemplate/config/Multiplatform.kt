package io.github.pawgli.kmpapptemplate.config

import io.github.pawgli.kmpapptemplate.extension.SupportedTargets
import io.github.pawgli.kmpapptemplate.extension.SupportedTargetsImpl
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
        implementation(libs.findLibrary("kotlinx-coroutines-core").get())
        implementation(libs.findLibrary("kotlinx-datetime").get())
      }
      commonTest.dependencies {
        implementation(libs.findLibrary("kotlin-test").get())
        implementation(libs.findLibrary("kotest-assertions").get())
        implementation(libs.findLibrary("turbine").get())
        implementation(libs.findLibrary("kotlinx-coroutines-test").get())
      }
      androidMain.dependencies {
        implementation(libs.findLibrary("kotlinx-coroutines-android").get())
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
