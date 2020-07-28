package me.robbin.wanandroid.app.network

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import me.robbin.mvvmscaffold.network.RetrofitClient
import me.robbin.mvvmscaffold.utils.Utils
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Retrofit
 * Create by Robbin at 2020/7/18
 */
class NetworkClient private constructor() : RetrofitClient() {

    companion object {
        val instance: NetworkClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkClient()
        }
    }

    // Cookie 本地存储
    private val cookieJar by lazy {
        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(Utils.getAPP()))
    }

    override fun setHttpClient(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.apply {
            cookieJar(cookieJar)
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)
        }
        return builder
    }

    /**
     * 清除本地 Cookie 缓存
     * Create by Robbin at 2020/7/20
     */
    fun clearCookie() {
        cookieJar.clear()
    }

}