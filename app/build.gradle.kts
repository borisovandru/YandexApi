import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}
android {
    compileSdk = Config.COMPILE_SDK
    buildToolsVersion = Config.BUILD_TOOLS

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.java_version
        targetCompatibility = Config.java_version
    }
    kotlinOptions {
        jvmTarget = Config.JVM_TARGET
    }
    buildFeatures {
        android.buildFeatures.viewBinding = true
    }
    buildTypes.forEach {
        val properties = Properties()
        properties.load(FileInputStream(project.rootProject.file("apiconfig.properties")))
        val appToken = properties.getProperty("token", "")
        it.buildConfigField("String", "API_TOKEN", appToken)
        val urlBase = properties.getProperty("base_url", "")
        it.buildConfigField("String", "BASE_URL", urlBase)
    }
}
dependencies {
    // Design
    implementation(Design.APPCOMPAT)
    implementation(Design.MATERIAL)
    implementation(Design.CONSTRAINT_LAYOUT)

    // Kotlin
    implementation(Kotlin.CORE)
    implementation(Kotlin.STDLIB)

    // LifeCycle
    implementation(LifeCycle.LIVEDATA_KTX)
    implementation(LifeCycle.VIEW_MODEL_KTX)
    // ViewBindingPropertyDelegate
    implementation(ViewBindingDelegate.DELEGATE)

    // Rx-Java
    implementation(RxJava.ANDROID)
    implementation(RxJava.RXJAVA)
    implementation(RxJava.RXKOTLIN)

    // Retrofit 2
    implementation(Retrofit2.RETROFIT)
    implementation(Retrofit2.CONVERTER_JSON)
    implementation(Retrofit2.LOGGING_INTERCEPTOR)
    implementation(Retrofit2.RXJAVA2_ADAPTER)
    implementation(Retrofit2.COROUTINES_ADAPTER)

    // Koin
    implementation(Koin.CORE)
    implementation(Koin.ANDROID)
    implementation(Koin.ANDROID_COMPAT)
    testImplementation(Koin.TEST)
    testImplementation(Koin.TEST_JUNIT4)

    // Coroutines
    implementation(Coroutines.CORE)
    implementation(Coroutines.ANDROID)

    // Cicerone
    implementation(Cicerone.CICERONE)

    // Room
    implementation(Room.RUN_TIME)
    implementation(Room.KTX)
    implementation(Room.RX_JAVA)
    kapt(Room.COMPILER)

    // Glide
    annotationProcessor(Glide.COMPILER)
    implementation(Glide.GLIDE)
    implementation(Glide.GLIDE_OKHTTP3)
    kapt(Glide.COMPILER)

    // Tests
    testImplementation(Tests.JUNIT)
    androidTestImplementation(Tests.TEST_EXT_JUNIT)
    androidTestImplementation(Tests.ESPRESSO)
}
