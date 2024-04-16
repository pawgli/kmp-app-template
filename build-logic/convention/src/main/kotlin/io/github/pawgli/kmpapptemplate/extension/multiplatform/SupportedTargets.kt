package io.github.pawgli.kmpapptemplate.extension.multiplatform

import org.jetbrains.kotlin.gradle.plugin.mpp.AbstractKotlinTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinAndroidTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

/**
 * Exposes the kotlin multiplatform targets that are registered as supported
 * in the [MultiplatformConventionPlugin] convention plugin and allows
 * for additional configuration of these targets.
 *
 * Example usage:
 * ```kotlin
 * kotlin {
 *   configure(supportedTargets.ios) {
 *     binaries.framework {
 *       ...
 *     }
 *   }
 * }
 * ```
 */
interface SupportedTargets {
  val allPlatforms: List<AbstractKotlinTarget>
  val android: List<KotlinAndroidTarget>
  val ios: List<KotlinNativeTarget>
}
