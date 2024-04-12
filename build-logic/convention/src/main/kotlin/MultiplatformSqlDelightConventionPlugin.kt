import io.github.pawgli.kmpapptemplate.config.configureSqlDelight
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformSqlDelightConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) = with(target) {
    pluginManager.apply("app.cash.sqldelight")

    configure<KotlinMultiplatformExtension> {
      configureSqlDelight(multiplatformExtension = this)
    }
  }
}
