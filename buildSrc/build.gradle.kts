plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.3.72"
    java
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.72")
    implementation(gradleApi())

}