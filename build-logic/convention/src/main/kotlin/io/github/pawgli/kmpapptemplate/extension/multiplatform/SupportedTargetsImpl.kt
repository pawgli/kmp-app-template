package io.github.pawgli.kmpapptemplate.extension.multiplatform

import org.jetbrains.kotlin.gradle.plugin.mpp.AbstractKotlinTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinAndroidTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

internal class SupportedTargetsImpl(
  override val android: List<KotlinAndroidTarget>,
  override val ios: List<KotlinNativeTarget>,
) : SupportedTargets {

  override val allPlatforms: List<AbstractKotlinTarget>
    get() = android + ios
}
