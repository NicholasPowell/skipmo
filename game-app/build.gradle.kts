plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.72"

}
dependencies {
    implementation(project(":core"))
    implementation("org.springframework.boot:spring-boot-starter-web:2.1.2.RELEASE")

}
