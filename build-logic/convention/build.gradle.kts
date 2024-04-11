plugins {
  `kotlin-dsl`
}

kotlin {
  jvmToolchain(17)
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
  plugins {
    registerConvention("AndroidAppConventionPlugin")
    registerConvention("AndroidAppComposeConventionPlugin")
  }
}

fun NamedDomainObjectContainer<PluginDeclaration>.registerConvention(name: String) {
  register(name) {
    id = name
    implementationClass = name
  }
}
