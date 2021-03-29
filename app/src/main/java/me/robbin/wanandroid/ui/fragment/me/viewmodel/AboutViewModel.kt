package me.robbin.wanandroid.ui.fragment.me.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.model.Licenses

/**
 * 关于软件界面 ViewModel
 * Create by Robbin at 2020/7/27
 */
class AboutViewModel : BaseViewModel() {

    val name: MutableLiveData<String> = MutableLiveData("Robbin Ma")

    val intro: MutableLiveData<String> = MutableLiveData("学习ing")

    val numberQQ: MutableLiveData<String> = MutableLiveData("QQ: 389352291")

    val numberWeChat: MutableLiveData<String> = MutableLiveData("WeChat: RobbinMa093")

    val numberEmail: MutableLiveData<String> = MutableLiveData("Email: RobbinMa093@gmail.com")

    val licenses: MutableLiveData<MutableList<Licenses>> = MutableLiveData()

    fun getLicenses() {
        val list = mutableListOf<Licenses>()
        list.add(
            Licenses(
                "Kotlin - JetBrains",
                "The Kotlin Programming Language",
                "https://github.com/JetBrains/kotlin"
            )
        )
        list.add(
            Licenses(
                "Kotlinx.Coroutines - Kotlin",
                "Library support for Kotlin coroutines",
                "https://github.com/Kotlin/kotlinx.coroutines"
            )
        )
        list.add(
            Licenses(
                "Retrofit - Square",
                "A type-safe HTTP client for Android and the JVM",
                "https://github.com/square/retrofit"
            )
        )
        list.add(
            Licenses(
                "OkHttp - Square",
                "Square's meticulous HTTP client for Java and Kotlin.",
                "https://github.com/square/okhttp"
            )
        )
        list.add(
            Licenses(
                "Gson - Google",
                "A Java serialization/deserialization library to convert Java Objects into JSON and back",
                "https://github.com/google/gson"
            )
        )
        list.add(
            Licenses(
                "Coil - coil-kt",
                "Image loading for Android backed by Kotlin Coroutines.",
                "https://github.com/coil-kt/coil"
            )
        )
        list.add(
            Licenses(
                "PersistentCookieJar - franmontiel",
                "A persistent CookieJar implementation for OkHttp 3 based on SharedPreferences.",
                "https://github.com/franmontiel/PersistentCookieJar"
            )
        )
        list.add(
            Licenses(
                "CircleImageView - hdodenhof",
                "A circular ImageView for Android",
                "https://github.com/hdodenhof/CircleImageView"
            )
        )
        list.add(
            Licenses(
                "BaseRecyclerViewAdapterHelper - CymChad",
                "Powerful and flexible RecyclerAdapter",
                "https://github.com/CymChad/BaseRecyclerViewAdapterHelper"
            )
        )
        list.add(
            Licenses(
                "MMKV - Tencent",
                "An efficient, small mobile key-value storage framework developed by WeChat. Works on Android, iOS, macOS, Windows, and POSIX.",
                "https://github.com/Tencent/MMKV"
            )
        )
        list.add(
            Licenses(
                "AgentWeb - Justson",
                "AgentWeb is a powerful library based on Android WebView.",
                "https://github.com/Justson/AgentWeb"
            )
        )
        list.add(
            Licenses(
                "banner - youth5201314",
                "Banner 2.0 来了！Android广告图片轮播控件，内部基于ViewPager2实现，Indicator和UI都可以自定义。",
                "https://github.com/youth5201314/banner"
            )
        )
        list.add(
            Licenses(
                "MVVMScaffold - RobbinM",
                "A toolkit help to build Android MVVM Application",
                "https://github.com/RobbinM/MVVMScaffold"
            )
        )
        licenses.value = list
    }

}