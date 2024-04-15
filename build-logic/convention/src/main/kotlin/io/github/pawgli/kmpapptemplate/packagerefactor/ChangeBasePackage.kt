package io.github.pawgli.kmpapptemplate.packagerefactor

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.provider.ValueSource
import org.gradle.api.provider.ValueSourceParameters
import org.gradle.api.tasks.TaskAction
import java.io.File
import javax.inject.Inject

private const val NEW_BASE_PACKAGE_KEY = "newBasePackage"
private const val OLD_BASE_PACKAGE = "io.github.pawgli.kmpapptemplate"

internal abstract class ChangeBasePackage : DefaultTask() {

  @get:Inject
  abstract val providerFactory: ProviderFactory

  private val changeBasePackageExecutor =
    providerFactory.of(ChangeBasePackageExecutor::class.java) {
      with(parameters) {
        projectDir.set(project.projectDir)
        newBasePackage.set(project.findProperty("newBasePackage") as? String)
      }
    }

  init {
    group = "Build Setup"
    description = "Changes the base package of the project, set -P$NEW_BASE_PACKAGE_KEY=com.your.package"
  }

  @TaskAction
  fun action() {
    changeBasePackageExecutor.get()
  }

  companion object {
    const val TASK_NAME = "changeBasePackage"
  }
}

private abstract class ChangeBasePackageExecutor : ValueSource<Unit, ChangeBasePackageParameters> {

  private val projectDir = parameters.projectDir.get()
  private val newBasePackage = parameters.newBasePackage.get()
  private val oldPath = OLD_BASE_PACKAGE.replace('.', '/')

  override fun obtain() {
    with(projectDir) {
      replaceBasePackageInExistingFiles()
      renameDirectories()
      removeOldDirectories()
    }
  }

  private fun File.replaceBasePackageInExistingFiles() {
    walkTopDown().forEach { file ->
      if (file.extension in listOf("kt", "java", "kts")) {
        var text = file.readText()
        text = text.replace(OLD_BASE_PACKAGE, newBasePackage)
        file.writeText(text)
      }
    }
  }

  private fun File.renameDirectories() {
    val newPath = newBasePackage.replace('.', '/')

    walkTopDown().forEach { file ->
      if (file.isDirectory && file.path.contains(oldPath)) {
        val newDirPath = file.path.replace(oldPath, newPath)
        val newDir = File(newDirPath)
        newDir.parentFile.mkdirs() // Create the necessary directories
        val isRenamingSuccessful = withRetry { file.renameTo(newDir) }
        if (isRenamingSuccessful) {
          println("Renamed directory: ${file.path} to $newDirPath")
        } else {
          println("Failed to rename directory: ${file.path} to $newDirPath")
        }
      }
    }
  }

  private fun File.removeOldDirectories() {
    walkBottomUp().forEach { file ->
      if (file.isDirectory && file.listFiles()?.isEmpty() == true) {
        file.deleteAlongWithEmptyParents()
      }
    }
  }

  private fun File.deleteAlongWithEmptyParents() {
    val parent = this.parentFile
    if (this.delete()) {
      parent?.let {
        if (it.isDirectory && it.listFiles()?.isEmpty() == true) {
          it.deleteAlongWithEmptyParents()
        }
      }
    }
  }
}

private interface ChangeBasePackageParameters : ValueSourceParameters {
  val projectDir: Property<File>
  val newBasePackage: Property<String>
}

private fun withRetry(tries: Int = 3, action: () -> Boolean): Boolean {
  var triesCount = 0
  do {
    if (action()) return true
    triesCount++
  } while (triesCount < tries)
  return false
}
