import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.spring") version "2.0.21"
}

group = "com.jeniatyt"
java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

//versions
val actuator = "3.4.0"
val web = "3.4.0"
val telegramSpring = "6.9.7.1"
val microutils = "3.0.5"

dependencies {
    //core
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.telegram:telegrambots-spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator:${actuator}")
    implementation("org.springframework.boot:spring-boot-starter-web:${web}")

    //kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //logging
    implementation("io.github.microutils:kotlin-logging-jvm")
}

dependencyManagement {
    dependencies {
        dependency("org.telegram:telegrambots-spring-boot-starter:${telegramSpring}")
        dependency("io.github.microutils:kotlin-logging-jvm:${microutils}")
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<Jar>("jar") {
    enabled = false
}

//generate Build Information
springBoot {
    buildInfo()
}
