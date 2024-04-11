plugins {
  with(libs.plugins) {
    alias(convention.android.app)
    alias(convention.android.app.compose)
  }
}

android {
  namespace = "io.github.pawgli.kmpapptemplate.android"
  defaultConfig {
    applicationId = "io.github.pawgli.kmpapptemplate.android"
    versionCode = 1
    versionName = "1.0"
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
}

dependencies {
  implementation(projects.shared)

  implementation(libs.androidx.activity.compose)
}
