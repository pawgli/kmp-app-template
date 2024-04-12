import com.android.build.api.dsl.LibraryExtension
import io.github.pawgli.kmpapptemplate.config.configureKotlin
import io.github.pawgli.kmpapptemplate.config.configureSdkVersions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {

  override fun apply(target: Project) = with(target) {
    with(pluginManager) {
      apply("com.android.library")
      apply("org.jetbrains.kotlin.android")
    }
    configureKotlin()
    configure<LibraryExtension> {
      configureSdkVersions(commonExtension = this)
    }
  }
}
