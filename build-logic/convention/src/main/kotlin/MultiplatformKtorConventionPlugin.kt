import io.github.pawgli.kmpapptemplate.config.configureKtor
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformKtorConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) = with(target) {
    pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

    configure<KotlinMultiplatformExtension> {
      configureKtor(multiplatformExtension = this)
    }
  }
}
