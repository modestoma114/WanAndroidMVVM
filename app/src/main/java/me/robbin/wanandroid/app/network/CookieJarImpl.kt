package me.robbin.wanandroid.app.network

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 *
 * Create by Robbin at 2020/7/18
 */
class CookieJarImpl(private val cookieStore: CookieStore) : CookieJar {

    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        cookieStore.add(url, cookies)
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        return cookieStore.get(url)
    }

    fun getCookieStore(): CookieStore {
        return cookieStore
    }

}