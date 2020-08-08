plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.3.72"
}

repositories {
    // The org.jetbrains.kotlin.jvm plugin requires a repository
    // where to download the Kotlin compiler dependencies from.
    jcenter()
}