import com.nilo.tasks.ExecTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("com.github.johnrengelman.shadow")
    id("io.spring.dependency-management")
    java
    maven
    id("org.springframework.boot.experimental.thin-launcher")
    id("org.jetbrains.kotlin.plugin.spring")
}
tasks.withType<Jar> {
    manifest {
        attributes ("Main-Class" to "com.nilo.oldmacdonald.OldMacDonaldApp" )
    }
}

tasks.register<ExecTask>("release" ) {
    command = "aws lambda update-function-code --function-name oldmacdonald --zip-file fileb:///Users/nickpowell/projects/skipmo/cloudfunctions/oldmacdonald/build/libs/oldmacdonald-$version-aws.jar"
}
tasks.named("release") { dependsOn(":assemble") }

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveClassifier.convention("aws" )
    archiveClassifier.set("aws" )
    dependencies {
        exclude(
                dependency("org.springframework.cloud:spring-cloud-function-web:3.0.9.RELEASE"))
    }
    // Required for Spring
    mergeServiceFiles()
    append("META-INF/spring.handlers")
    append("META-INF/spring.schemas")
    append("META-INF/spring.tooling")
    transform(com.github.jengelman.gradle.plugins.shadow.transformers.PropertiesFileTransformer::class.java) {
        paths = mutableListOf("META-INF/spring.factories")
        mergeStrategy = "append"
    }
}
version = "0.0.1-SNAPSHOT"
group = "com.nilo.cloudfunctions"

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven( url="https://repo.spring.io/snapshot" )
    maven( url ="https://repo.spring.io/milestone" )
}

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "11" }
tasks.withType<Test>().configureEach { useJUnitPlatform() }

dependencies {
    implementation(com.nilo.SpringCloud.Function.Kotlin )
    implementation(com.nilo.SpringCloud.Function.Starter.Web)
    implementation(com.nilo.SpringCloud.Function.Adapter.Aws)
    implementation(com.nilo.Kotlin.StdLib.Sdk1_8)
    implementation(com.nilo.Jackson.Module.Kotlin)
    implementation(com.nilo.Aws.Lambda.Java.Events)
    implementation(com.nilo.Aws.Lambda.Java.Core)
}
