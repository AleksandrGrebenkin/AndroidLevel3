import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.github.aleksandrgrebenkin.androidlevel3"
    const val compile_sdk = 30
    const val min_sdk = 21
    const val target_sdk = 30
    const val build_tools_version = "30.0.3"
    const val jvm_target = "1.8"
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val meaningScreen = ":meaningScreen"
    const val searchScreen = ":searchScreen"
    const val historyScreen = ":historyScreen"
    const val navigation = ":navigation"
}

object Versions {
    //Kotlin
    const val kotlinStdlib = "1.4.31"
    const val kotlinCore = "1.3.2"
    const val kotlinCoroutinesCore = "1.4.2"
    const val kotlinCoroutinesAndroid = "1.4.1"

    //Navigation
    const val navigationFragment = "2.3.3"
    const val navigationUI = "2.3.3"

    //Glide
    const val glide = "4.11.0"
    const val glideCompiler = "4.11.0"

    //Design
    const val appcompat = "1.2.0"
    const val material = "1.3.0"
    const val constraintLayout = "2.0.4"

    //Retrofit
    const val retrofit = "2.9.0"
    const val retrofitConverterGson = "2.9.0"
    const val retrofitInterceptor = "5.0.0-alpha.2"
    const val retrofitCoroutinesAdapter = "0.9.2"

    //Lifecycle
    const val lifecycleCommon = "1.1.1"
    const val lifecycleExtensions = "1.1.1"

    //Room
    const val roomRuntime = "2.2.6"
    const val roomKtx = "2.2.6"
    const val roomCompiler = "2.2.6"

    //Koin
    const val koinAndroid = "2.0.1"
    const val koinViewModel = "2.0.1"

    // Test
    const val jUnit = "4.13.2"
    const val androidJUnit = "1.1.2"
    const val androidEspresso = "3.3.0"
}

object Kotlin {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinStdlib}"
    const val core = "androidx.core:core-ktx:${Versions.kotlinCore}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutinesAndroid}"
}

object Navigation {
    const val fragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragment}"
    const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigationUI}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverterGson}"
    const val interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitInterceptor}"
    const val coroutines_adapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutinesAdapter}"
}

object Lifecycle {
    const val extensions = "android.arch.lifecycle:extensions:${Versions.lifecycleExtensions}"
    const val common = "android.arch.lifecycle:common-java8:${Versions.lifecycleCommon}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.roomRuntime}"
    const val ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
}

object Koin {
    const val android = "org.koin:koin-android:${Versions.koinAndroid}"
    const val view_model = "org.koin:koin-android-viewmodel:${Versions.koinViewModel}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val android_junit = "androidx.test.ext:junit:${Versions.androidJUnit}"
    const val android_espresso = "androidx.test.espresso:espresso-core:${Versions.androidEspresso}"
}