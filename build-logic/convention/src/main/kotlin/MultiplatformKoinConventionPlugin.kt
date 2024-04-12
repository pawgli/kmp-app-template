import io.github.pawgli.kmpapptemplate.config.configureKoin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformKoinConventionPlugin : Plugin<Project> {

  override fun apply(target: Project) = with(target) {
    pluginManager.apply("org.jetbrains.kotlin.multiplatform")

    configure<KotlinMultiplatformExtension> {
      configureKoin(multiplatformExtension = this)
    }
  }
}
