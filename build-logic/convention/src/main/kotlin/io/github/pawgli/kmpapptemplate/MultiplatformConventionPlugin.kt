package io.github.pawgli.kmpapptemplate

import com.android.build.api.dsl.LibraryExtension
import io.github.pawgli.kmpapptemplate.config.configureKotlin
import io.github.pawgli.kmpapptemplate.config.configureKotlinMultiplatform
import io.github.pawgli.kmpapptemplate.config.configureSdkVersions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformConventionPlugin : Plugin<Project> {

  override fun apply(target: Project) = with(target) {
    with(pluginManager) {
      apply("com.android.library")
      apply("org.jetbrains.kotlin.multiplatform")
    }
    configureKotlin()
    tasks.registerAllUnitTestsTask()
    configure<LibraryExtension> {
      configureSdkVersions(commonExtension = this)
    }
    configure<KotlinMultiplatformExtension> {
      configureKotlinMultiplatform(multiplatformExtension = this)
    }
  }
}

private fun TaskContainer.registerAllUnitTestsTask() {
  register("allUnitTests") {
    group = "verification"
    description = "Runs all the unit tests for both platforms."

    dependsOn("test")
    dependsOn("iosSimulatorArm64Test") // Kotlin/Native
  }
}
