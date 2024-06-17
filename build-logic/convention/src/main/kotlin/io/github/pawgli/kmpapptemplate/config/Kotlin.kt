package io.github.pawgli.kmpapptemplate.config

import io.github.pawgli.kmpapptemplate.util.getVersion
import io.github.pawgli.kmpapptemplate.util.libs
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

internal fun Project.configureKotlin() {
  with(kotlinExtension) {
    jvmToolchain(jdkVersion = libs.getVersion("java").toInt())
  }
}
