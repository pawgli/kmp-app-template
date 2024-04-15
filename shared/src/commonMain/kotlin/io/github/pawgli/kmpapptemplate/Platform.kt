package io.github.pawgli.kmpapptemplate

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform
