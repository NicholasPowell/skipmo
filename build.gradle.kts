import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    maven
    `java-library`
    id("org.springframework.boot") version "2.1.3.RELEASE" apply false
    id("com.palantir.docker") version "0.22.1" apply false
    kotlin("jvm") version "1.3.72"
}



tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


allprojects {
    apply(plugin = "java")
    group = "com.nilo.skipmo"
    version = "0.0.1-SNAPSHOT"

//    java {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.72")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
    }


    repositories {
        mavenLocal()
        mavenCentral()
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
//  task.testJar<Jar>(type: Jar) {
//    manifest {
//        attributes "Manifest-Version": "1.0",
//                "Implementation-Title": "xyz", 
//                "Implementation-Version": version, 
//                "Main-Class": "TestMain", 
//                "SplashScreen-Image": "splashScreenTest.png"
//    }
    //classifier = "tests"
//    baseName = project.name + "-test"
//    from sourceSets.test.output+sourceSets.test.allSource
//  }

//  configurations {
//    testArtifacts
//  }

    artifacts {
//    testArtifacts testJar
    }

}



