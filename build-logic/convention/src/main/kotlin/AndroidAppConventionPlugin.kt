import com.android.build.api.dsl.ApplicationExtension
import io.github.pawgli.kmpapptemplate.ConventionDefaults
import io.github.pawgli.kmpapptemplate.config.configureKotlin
import io.github.pawgli.kmpapptemplate.config.configureSdkVersions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidAppConventionPlugin : Plugin<Project> {

  override fun apply(target: Project) = with(target) {
    with(pluginManager) {
      apply("com.android.application")
      apply("org.jetbrains.kotlin.android")
    }
    configureKotlin()
    configure<ApplicationExtension> {
      defaultConfig.targetSdk = ConventionDefaults.TargetSdk
      configureSdkVersions(commonExtension = this)
    }
  }
}
