plugins {
  alias(libs.plugins.convention.multiplatform)
}

android {
  namespace = "io.github.pawgli.kmpapptemplate"
}

kotlin {
  configure(supportedTargets.ios) {
    binaries.framework {
      baseName = "shared"
      isStatic = true
    }
  }

  sourceSets {
    commonMain.dependencies {
      // put your multiplatform dependencies here
    }
  }
}
