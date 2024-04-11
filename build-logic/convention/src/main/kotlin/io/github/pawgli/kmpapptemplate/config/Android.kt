package io.github.pawgli.kmpapptemplate.config

import com.android.build.api.dsl.CommonExtension
import io.github.pawgli.kmpapptemplate.ConventionDefaults
import io.github.pawgli.kmpapptemplate.util.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureSdkVersions(
  commonExtension: CommonExtension<*, *, *, *, *>,
  minSdk: Int = 29,
) {
  with(commonExtension) {
    compileSdk = ConventionDefaults.CompileSdk

    defaultConfig {
      this.minSdk = minSdk
    }

    if (minSdk < ConventionDefaults.MinJava8Sdk) setUpDesugaring(commonExtension)
  }
}

private fun Project.setUpDesugaring(extension: CommonExtension<*, *, *, *, *>) {
  extension.compileOptions {
    isCoreLibraryDesugaringEnabled = true
  }
  dependencies {
    add("coreLibraryDesugaring", libs.findLibrary("android-desugaring").get())
  }
}
