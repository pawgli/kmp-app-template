plugins {
  with(libs.plugins) {
    alias(android.app) apply false
    alias(android.library) apply false
    alias(kotlin.android) apply false
    alias(kotlin.multiplatform) apply false
    alias(kotlin.serialization) apply false
    alias(sqlDelight) apply false

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
}
