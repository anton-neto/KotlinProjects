// build.gradle.kts (Projeto)
plugins {
    id("com.android.application") version "8.2.0" // Verifique a versão mais recente compatível
    kotlin("android") version "1.8.0" // Versão do Kotlin
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.0") // Versão do plugin Android
        classpath(kotlin("gradle-plugin", version = "1.8.0")) // Versão do plugin Kotlin
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
