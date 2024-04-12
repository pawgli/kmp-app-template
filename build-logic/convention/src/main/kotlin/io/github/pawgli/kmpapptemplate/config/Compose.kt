package io.github.pawgli.kmpapptemplate.config

import com.android.build.api.dsl.CommonExtension
import io.github.pawgli.kmpapptemplate.util.getLibrary
import io.github.pawgli.kmpapptemplate.util.getVersion
import io.github.pawgli.kmpapptemplate.util.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCompose(
  extension: CommonExtension<*, *, *, *, *>,
) {
  extension.apply {
    buildFeatures {
      compose = true
    }

    composeOptions {
      kotlinCompilerExtensionVersion = libs.getVersion("composeCompiler")
    }
  }

  dependencies {
    val bom = libs.getLibrary("compose-bom")
    add("implementation", platform(bom))
    add("implementation", libs.getLibrary("compose-ui"))
    add("implementation", libs.getLibrary("compose-material3"))
    add("implementation", libs.getLibrary("compose-uiPreview"))
    add("debugImplementation", libs.getLibrary("compose-uiTooling"))
  }
}
