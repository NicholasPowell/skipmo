import com.nilo.Aws
import com.nilo.Jackson
import com.nilo.Kotlin.StdLib
import com.nilo.SpringCloud
import com.nilo.tasks.ExecTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    maven
    kotlin("jvm")
    id("org.springframework.boot")
    id("com.github.johnrengelman.shadow")
    id("io.spring.dependency-management")
    id("org.jetbrains.kotlin.plugin.spring")
    id("org.springframework.boot.experimental.thin-launcher")
}

repositories {
//    mavenLocal()
    mavenCentral()
    jcenter()
    maven(url = "https://repo.spring.io/snapshot")
    maven(url = "https://repo.spring.io/milestone")
}

tasks.withType<Jar> { manifest { attributes("Main-Class" to "com.nilo.mary.MaryApp") } }
tasks.named("assemble") { dependsOn(":mary:shadowJar", ":mary:thinJar") }
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveClassifier.convention("aws")
    archiveClassifier.set("aws")
    dependencies {
        exclude(dependency("org.springframework.cloud:spring-cloud-function-web:3.0.9.RELEASE"))
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

tasks.register<ExecTask>("release" ) {
    command = "aws lambda update-function-code --function-name mary --zip-file fileb:///Users/nickpowell/projects/skipmo/cloudfunctions/mary/build/libs/mary-$version-aws.jar"
}

tasks.named("release") { dependsOn(":assemble") }

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "11" }
tasks.withType<Test>().configureEach { useJUnitPlatform() }

dependencies {
    implementation(project(":core"))
    implementation(project(":port"))
    implementation("org.postgresql:postgresql:42.2.15")
    implementation("com.github.seratch:kotliquery:1.3.1")
    implementation(SpringCloud.Function.Kotlin )
    implementation(SpringCloud.Function.Starter.Web)
    implementation(SpringCloud.Function.Adapter.Aws)
    implementation(StdLib.Sdk1_8)
    implementation(Jackson.Module.Kotlin)
    implementation(Aws.Lambda.Java.Events)
    implementation(Aws.Lambda.Java.Core)
}