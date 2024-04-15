import io.gitlab.arturbosch.detekt.Detekt

plugins {
  with(libs.plugins) {
    alias(android.app) apply false
    alias(android.library) apply false
    alias(kotlin.android) apply false
    alias(kotlin.multiplatform) apply false
    alias(kotlin.serialization) apply false
    alias(sqlDelight) apply false
    alias(detekt)

    // Convention plugins
    alias(convention.android.app) apply false
    alias(convention.android.app.compose) apply false
    alias(convention.android.library) apply false
    alias(convention.android.library.compose) apply false
    alias(convention.multiplatform) apply false
    alias(convention.multiplatform.koin) apply false
    alias(convention.multiplatform.ktor) apply false
    alias(convention.multiplatform.sqlDelight) apply false
  }
  id("BasePackagePlugin")
}

dependencies {
  detektPlugins(libs.detekt.formatting)
}

detekt {
  buildUponDefaultConfig = true
  allRules = false
  config.setFrom("$projectDir/detekt.yml")
  autoCorrect = true
}

tasks.withType<Detekt>().configureEach {
  parallel = true
  setSource(files(projectDir))
  exclude("**/test/**", "**/androidTest/**", "**/build/**", "**/androidUnitTest/**", "**/commonTest/**")
  reports {
    html.required.set(false)
    xml.required.set(true)
    txt.required.set(false)
    sarif.required.set(false)
    md.required.set(false)
  }
}

tasks.withType<Detekt>().configureEach {
  jvmTarget = libs.versions.java.get()
}
