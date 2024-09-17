// build.gradle.kts (Módulo)
plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 34 // ou a versão desejada

    defaultConfig {
        applicationId = "com.example.dados"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0" // Versão do Compose
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/compose_release.kotlin_module")
        }
    }
}

dependencies {
    implementation("androidx.compose.ui:ui:1.4.0") // Versão do Jetpack Compose
    implementation("androidx.compose.material3:material3:1.0.0") // Material3
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0") // Ferramentas de visualização
    implementation("androidx.navigation:navigation-compose:2.7.0") // Navegação Compose

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0") // Versão do Kotlin

    // Testes
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.0") // Ferramentas de depuração
}
