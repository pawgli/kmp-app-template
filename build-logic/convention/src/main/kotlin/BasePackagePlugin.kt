import io.github.pawgli.kmpapptemplate.packagerefactor.ChangeBasePackage
import org.gradle.api.Plugin
import org.gradle.api.Project

class BasePackagePlugin : Plugin<Project> {
  override fun apply(target: Project) {
    target.tasks.register(ChangeBasePackage.TASK_NAME, ChangeBasePackage::class.java)
  }
}
