package me.robbin.buildSrc

object Versions {

    const val compilerSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 22
    const val targetSdkVersion = 30
    const val versionCode = 1
    const val versionName = "1.0.0"

}

object Dependencies {

    const val buildGradle = "com.android.tools.build:gradle:4.1.3"

    object Kotlin {
        private const val version = "1.4.31"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:$version"

        object Coroutines {
            private const val version = "1.4.3"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }

    }

    object Lifecycle {
        private const val version = "2.3.1"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.3.2"
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val material = "com.google.android.material:material:1.3.0"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
        const val paging = "androidx.paging:paging-runtime-ktx:3.0.0-beta03"
    }

    object Net {

        const val android = "com.github.liangjingkanji:Net:2.3.11"

        object Moshi {
            const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:1.11.0"
        }

    }

    object ThirdParty {
        const val BRVAH = "com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.6"
        const val circleImage = "de.hdodenhof:circleimageview:3.1.0"
        const val persistentCookie = "com.github.franmontiel:PersistentCookieJar:1.0.1"
        const val coil = "io.coil-kt:coil:1.1.1"
        const val MMKV = "com.tencent:mmkv-static:1.2.7"
        const val agentWeb = "com.just.agentweb:agentweb-androidx:4.1.4"
        const val banner = "com.youth.banner:banner:2.1.0"
    }

}