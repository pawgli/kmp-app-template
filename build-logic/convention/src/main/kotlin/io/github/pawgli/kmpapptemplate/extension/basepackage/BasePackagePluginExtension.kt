package io.github.pawgli.kmpapptemplate.extension.basepackage

import io.github.pawgli.kmpapptemplate.BasePackagePlugin
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

/**
 * The extension for the [BasePackagePlugin], allowing to set up the plugin's properties.
 *
 * @property basePackage The current base package of the project.
 * @property fileTypesToUpdate The list of file types that should be updated,
 * when the base package changes. Current base package occurrences in these files,
 * will be replaced with the new base package.
 */
interface BasePackagePluginExtension {
  val basePackage: Property<String>
  val fileTypesToUpdate: ListProperty<String>
}
