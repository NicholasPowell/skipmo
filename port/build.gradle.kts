plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.72"
}
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
    implementation("org.springframework.boot:spring-boot-starter-web:2.1.2.RELEASE")

    testImplementation("org.springframework:spring-test:5.1.8.RELEASE")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.1.2.RELEASE")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.4.2")
}
