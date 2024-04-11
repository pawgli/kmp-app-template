package io.github.pawgli.kmpapptemplate.util

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.VersionConstraint
import org.gradle.kotlin.dsl.getByType
import java.util.Optional

internal val Project.libs: VersionCatalog
  get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Optional<VersionConstraint>.getAsString() = get().toString()
