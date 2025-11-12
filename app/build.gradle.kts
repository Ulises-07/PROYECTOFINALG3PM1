plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.proyectofinalg3pm1"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.proyectofinalg3pm1"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Core (Obligatoria)
    implementation("io.supabase.kt:supabase-client:2.5.1")
// Autenticación (Login, Registro, Password)
    implementation("io.supabase.kt:gotrue-client:2.5.1")
// Base de datos (Select, Insert, Update)
    implementation("io.supabase.kt:postgrest-client:2.5.1")
// Almacenamiento (Subir/Bajar archivos)
    implementation("io.supabase.kt:storage-client:2.5.1")
// Tiempo Real (Para el feed y notificaciones)
    implementation("io.supabase.kt:realtime-client:2.5.1")
// Para que funcione en Android (manejo de corutinas)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}