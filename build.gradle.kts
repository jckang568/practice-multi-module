import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22" apply false
    id("org.springframework.boot") version "3.1.6" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
}


java {
    sourceCompatibility = JavaVersion.VERSION_17
}


allprojects {
    group = "dev.jayce"
    version = "0.0.1-SNAPSHOT"
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply (plugin = "org.jetbrains.kotlin.jvm")
    apply (plugin = "org.jetbrains.kotlin.plugin.spring")
    apply (plugin = "org.springframework.boot")
    apply (plugin = "io.spring.dependency-management")
    dependencies {
        // implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.getByName("bootJar") {
        enabled = false
    }

    tasks.getByName("jar") {
        enabled = false
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    /*tasks.bootBuildImage {
        builder.set("paketobuildpacks/builder-jammy-base:latest")
    }*/
}


