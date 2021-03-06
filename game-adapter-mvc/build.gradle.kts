plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.72"

}
dependencies {
    implementation(project(":core"))
    implementation(project(":port"))
    implementation("org.springframework.boot:spring-boot-starter-web:2.1.2.RELEASE")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")

    testImplementation("org.springframework:spring-test:5.1.8.RELEASE")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.1.2.RELEASE")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.4.2")

}
