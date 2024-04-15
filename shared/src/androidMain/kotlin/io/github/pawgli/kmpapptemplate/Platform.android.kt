package io.github.pawgli.kmpapptemplate

class AndroidPlatform : Platform {
  override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
