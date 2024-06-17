plugins {
  `kotlin-dsl`
}

kotlin {
  jvmToolchain(libs.versions.java.get().toInt())
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
  plugins {
    registerConvention("AndroidAppConventionPlugin", id = "android.app")
    registerConvention("AndroidAppComposeConventionPlugin", id = "android.app.compose")
    registerConvention("AndroidLibraryConventionPlugin", id = "android.library")
    registerConvention("AndroidLibraryComposeConventionPlugin", id = "android.library.compose")
    registerConvention("MultiplatformConventionPlugin", id = "multiplatform")
    registerConvention("MultiplatformKoinConventionPlugin", id = "multiplatform.koin")
    registerConvention("MultiplatformKtorConventionPlugin", id = "multiplatform.ktor")
    registerConvention("MultiplatformSqlDelightConventionPlugin", id = "multiplatform.sqlDelight")
    registerConvention("BasePackagePlugin", id = "basePackage")
  }
}

fun NamedDomainObjectContainer<PluginDeclaration>.registerConvention(name: String) {
  register(name) {
    id = name
    implementationClass = name
  }
}

private fun NamedDomainObjectContainer<PluginDeclaration>.registerConvention(
  implementationClass: String,
  id: String,
) {
  register(implementationClass) {
    this.id = "io.github.pawgli.kmpapptemplate.$id"
    this.implementationClass = "io.github.pawgli.kmpapptemplate.$implementationClass"
  }
}
