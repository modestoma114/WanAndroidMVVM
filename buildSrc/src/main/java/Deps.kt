object Versions {
    const val compilerSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 22
    const val targetSdkVersion = 30

    const val buildGradleVersion = "4.1.1"

    const val kotlinVersion = "1.4.21"

    const val androidxCoreVersion = "1.3.2"
    const val androidxAppcompatVersion = "1.2.0"

    const val constraintLayoutVersion = "2.0.4"
    const val materialVersion = "1.2.1"
    const val swipeRefreshLayoutVersion = "1.1.0"

    const val pagingVersion = "3.0.0-alpha03"

    const val BRVAHVersion = "3.0.6"
    const val circleImageVersion = "3.1.0"
    const val persistentCookieVersion = "v1.0.1"
    const val coilVersion = "1.1.0"
    const val mmkvVersion = "1.2.6"
    const val agentWebVersion = "4.1.4"
    const val bannerVersion = "2.1.0"
}

object Dependencies {
    const val buildGradle = "com.android.tools.build:gradle:${Versions.buildGradleVersion}"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"

    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCoreVersion}"
    const val androidxAppcompat = "androidx.appcompat:appcompat:${Versions.androidxAppcompatVersion}"

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayoutVersion}"

    const val pagingRuntimeKtx = "androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}"

    const val BRVAH = "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.BRVAHVersion}"
    const val circleImage = "de.hdodenhof:circleimageview:${Versions.circleImageVersion}"
    const val persistentCookie = "com.github.franmontiel:PersistentCookieJar:${Versions.persistentCookieVersion}"
    const val coil = "io.coil-kt:coil:${Versions.coilVersion}"
    const val MMKV = "com.tencent:mmkv-static:${Versions.mmkvVersion}"
    const val agentWeb = "com.just.agentweb:agentweb:${Versions.agentWebVersion}"
    const val banner = "com.youth.banner:banner:${Versions.bannerVersion}"
}