package io.github.pawgli.kmpapptemplate.config

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

internal fun Project.configureKotlin() {
  with(kotlinExtension) {
    jvmToolchain(jdkVersion = 17)
  }
}
