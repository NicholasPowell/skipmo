pluginManagement {
    plugins {
        kotlin("jvm") version "1.3.72"
        id("org.springframework.boot") version "2.3.3.RELEASE"
        id("com.github.johnrengelman.shadow") version "6.0.0"
        id("io.spring.dependency-management") version "1.0.10.RELEASE"
        java
        maven
        id("org.springframework.boot.experimental.thin-launcher") version "1.0.25.RELEASE"
        id("org.jetbrains.kotlin.plugin.spring") version "1.3.72"
    }
    repositories {
//        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://repo.spring.io/milestone")
        jcenter()
    }
    resolutionStrategy{
        eachPlugin{
            if (requested.id.id=="org.springframework.boot.experimental.thin-launcher"){
                useModule("org.springframework.boot.experimental:spring-boot-thin-gradle-plugin:1.0.25.RELEASE")
            }
        }
    }

}

rootProject.name = "skipmo"
include(
        "port",
        "game-adapter-mvc",
        "core"

)
include("mary")
include("oldmacdonald")
include("aws")
project(":mary").projectDir= File("cloudfunctions/mary")
project(":oldmacdonald").projectDir= File("cloudfunctions/oldmacdonald")
