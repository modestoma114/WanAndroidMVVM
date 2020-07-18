package me.robbin.wanandroid.app.network

import me.robbin.mvvmscaffold.network.RetrofitClient
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 *
 * Create by Robbin at 2020/7/18
 */
class NetworkClient : RetrofitClient() {

    companion object {
        val instance: NetworkClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkClient()
        }
    }

    override fun setHttpClient(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.apply {
            cookieJar(CookieJarImpl(PersistentCookieStore()))
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)
        }
        return builder
    }

}