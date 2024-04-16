import io.github.pawgli.kmpapptemplate.extension.basepackage.BasePackagePluginExtension
import io.github.pawgli.kmpapptemplate.packagerefactor.ChangeBasePackage
import org.gradle.api.Plugin
import org.gradle.api.Project

class BasePackagePlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      val extension: BasePackagePluginExtension = createBasePackageExtension()

      afterEvaluate {
        extension.apply {
          basePackage.convention(DEFAULT_BASE_PACKAGE)
          fileTypesToUpdate.convention(DefaultFilesToUpdate)
        }
        registerChangeBasePackageTask(extension)
      }
    }
  }

  internal companion object {
    const val EXTENSION_NAME = "basePackagePlugin"
    private const val DEFAULT_BASE_PACKAGE = "com.example.app"
    private val DefaultFilesToUpdate =
      listOf("kt", "kts", "java", "gradle", "properties", "yml", "xml", "md")
  }
}

private fun Project.createBasePackageExtension(): BasePackagePluginExtension =
  extensions.create(BasePackagePlugin.EXTENSION_NAME, BasePackagePluginExtension::class.java)

private fun Project.registerChangeBasePackageTask(
  extension: BasePackagePluginExtension,
) {
  tasks.register(ChangeBasePackage.TASK_NAME, ChangeBasePackage::class.java) {
    basePackage.set(extension.basePackage)
    extensionsOfFilesToUpdate.set(extension.fileTypesToUpdate)
  }
}
