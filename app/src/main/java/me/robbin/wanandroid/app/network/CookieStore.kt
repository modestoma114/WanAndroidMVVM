package me.robbin.wanandroid.app.network

import okhttp3.Cookie
import okhttp3.HttpUrl

/**
 *
 * Create by Robbin at 2020/7/18
 */
interface CookieStore {

    /**
     * 添加Cookie
     * Create by Robbin at 2020/7/18
     */
    fun add(httpUrl: HttpUrl, cookie: Cookie)

    /**
     * 添加指定HttpUrl Cookie集合
     * Create by Robbin at 2020/7/18
     */
    fun add(httpUrl: HttpUrl, cookies: MutableList<Cookie>)

    /**
     * 根据HttpUrl从缓存中读取Cookie集合
     * Create by Robbin at 2020/7/18
     */
    fun get(httpUrl: HttpUrl): MutableList<Cookie>

    /**
     * 获取所有缓存Cookie
     * Create by Robbin at 2020/7/18
     */
    fun getCookies(): MutableList<Cookie>

    /**
     * 移除指定HttpUrl Cookie
     * Create by Robbin at 2020/7/18
     */
    fun remove(httpUrl: HttpUrl, cookie: Cookie): Boolean

    /**
     * 移除所有Cookie
     * Create by Robbin at 2020/7/18
     */
    fun removeAll(): Boolean

}