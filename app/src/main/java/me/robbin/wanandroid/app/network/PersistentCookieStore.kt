package me.robbin.wanandroid.app.network

import android.content.Context
import android.text.TextUtils
import android.util.Log
import me.robbin.mvvmscaffold.utils.SPUtils
import okhttp3.Cookie
import okhttp3.HttpUrl
import java.io.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.HashMap
import kotlin.experimental.and

/**
 *
 * Create by Robbin at 2020/7/18
 */
class PersistentCookieStore : CookieStore {

    companion object {
        private const val HOST_NAME_PREFIX = "host_"
        private const val COOKIE_NAME_PREFIX = "cookie_"
        private const val LOG_TAG = "PersistentCookieStore"
    }

    private val cookiePrefs: SPUtils = SPUtils.getInstance("CookiePrefsFile", Context.MODE_PRIVATE)
    private val cookies: HashMap<String, ConcurrentHashMap<String, Cookie>> = HashMap()
    private var omitNonPersistentCookies = false

    init {
        val tempCookieMap = HashMap<Any, Any>(cookiePrefs.getAll())
        run breaking@{
            tempCookieMap.keys.forEach { key ->
                if (key !is String || !key.contains(HOST_NAME_PREFIX))
                    return@breaking
                val cookieName = tempCookieMap[key] as String
                if (TextUtils.isEmpty(cookieName))
                    return@breaking
                if (!cookies.containsKey(key))
                    cookies[key] = ConcurrentHashMap()
                val cookieNameArr = cookieName.split(",")
                run breaking2@{
                    cookieNameArr.forEach { name ->
                        val encodedCookie = cookiePrefs.getString(COOKIE_NAME_PREFIX + name, "")
                        if (encodedCookie == "")
                            return@breaking2
                        val decodedCookie = decodeCookie(encodedCookie!!)
                        if (decodedCookie != null)
                            cookies[key]?.put(name, decodedCookie)
                    }
                }
            }
        }
        tempCookieMap.clear()
        clearExpired()
    }

    private fun clearExpired() {
        cookies.keys.forEach { key ->
            var changeFlag = false
            cookies[key]?.entries?.forEach { entry ->
                val name = entry.key
                val cookie = entry.value
                if (isCookieExpired(cookie)) {
                    cookies[key]?.remove(name)
                    cookiePrefs.remove(COOKIE_NAME_PREFIX + name)
                    changeFlag = true
                }
            }
            if (changeFlag) {
                cookiePrefs.put(key, TextUtils.join(",", cookies.keys))
            }
        }
    }

    override fun add(httpUrl: HttpUrl, cookie: Cookie) {
        if (omitNonPersistentCookies && !cookie.persistent())
            return
        val name = cookieName(cookie)
        val hostKey = hostName(httpUrl)

        if (cookies.containsKey(hostKey))
            cookies[hostKey] = ConcurrentHashMap()
        cookies[hostKey]?.put(name!!, cookie)
        cookies[hostKey]?.keys?.let { TextUtils.join(",", it) }?.let {
            cookiePrefs.put(
                hostKey,
                it
            )
        }
        encodeCookie(SerializableCookie(cookie))?.let {
            cookiePrefs.put(
                COOKIE_NAME_PREFIX + name,
                it
            )
        }
    }

    override fun add(httpUrl: HttpUrl, cookies: MutableList<Cookie>) {
        for (cookie in cookies) {
            if (isCookieExpired(cookie)) {
                continue
            }
            add(httpUrl, cookie)
        }
    }

    override fun get(httpUrl: HttpUrl): MutableList<Cookie> {
        return this[this.hostName(httpUrl)]
    }

    override fun getCookies(): MutableList<Cookie> {
        val result = ArrayList<Cookie>()
        for (hostKey in cookies.keys) {
            result.addAll(this[hostKey])
        }
        return result
    }

    /** 获取cookie集合  */
    private operator fun get(hostKey: String): MutableList<Cookie> {
        val result = ArrayList<Cookie>()
        if (cookies.containsKey(hostKey)) {
            val cookies: Collection<Cookie> =
                cookies[hostKey]!!.values
            for (cookie in cookies) {
                if (isCookieExpired(cookie)) {
                    this.remove(hostKey, cookie)
                } else {
                    result.add(cookie)
                }
            }
        }
        return result
    }

    override fun remove(httpUrl: HttpUrl, cookie: Cookie): Boolean {
        return this.remove(hostName(httpUrl), cookie)
    }

    /** 从缓存中移除cookie  */
    private fun remove(hostKey: String, cookie: Cookie): Boolean {
        val name = cookieName(cookie)
        if (cookies.containsKey(hostKey) && cookies[hostKey]!!.containsKey(name)) {
            // 从内存中移除httpUrl对应的cookie
            cookies[hostKey]!!.remove(name)

            // 从本地缓存中移出对应cookie
            cookiePrefs.remove(COOKIE_NAME_PREFIX + name)

            // 保存httpUrl对应的所有cookie的name
            cookiePrefs.put(hostKey, TextUtils.join(",", cookies[hostKey]!!.keys))
            return true
        }
        return false
    }

    override fun removeAll(): Boolean {
        cookiePrefs.clear()
        cookies.clear()
        return true
    }

    fun setOmitNonPersistentCookies(omitNonPersistentCookies: Boolean) {
        this.omitNonPersistentCookies = omitNonPersistentCookies
    }

    /**
     * 判断Cookie是否失效
     * Create by Robbin at 2020/7/18
     */
    private fun isCookieExpired(cookie: Cookie): Boolean {
        return cookie.expiresAt() < System.currentTimeMillis()
    }

    private fun hostName(httpUrl: HttpUrl): String {
        return if (httpUrl.host().startsWith(HOST_NAME_PREFIX)) httpUrl.host()
        else HOST_NAME_PREFIX + httpUrl.host()
    }

    private fun cookieName(cookie: Cookie?): String? {
        return if (cookie == null) null else cookie.name() + cookie.domain()
    }

    private fun encodeCookie(cookie: SerializableCookie?): String? {
        if (cookie == null) return null
        val os = ByteArrayOutputStream()
        try {
            val outputStream = ObjectOutputStream(os)
            outputStream.writeObject(cookie)
        } catch (e: IOException) {
            Log.d(LOG_TAG, "IOException in encodeCookie", e)
            return null
        }
        return byteArrayToHexString(os.toByteArray())
    }

    private fun decodeCookie(cookieString: String): Cookie? {
        val bytes = hexStringToByteArray(cookieString)
        val byteArrayInputStream = ByteArrayInputStream(bytes)
        var cookie: Cookie? = null
        try {
            val objectInputStream = ObjectInputStream(byteArrayInputStream)
            cookie = (objectInputStream.readObject() as SerializableCookie).getCookie()
        } catch (e: IOException) {
            Log.d(LOG_TAG, "IOException in decodeCookie", e)
        } catch (e: ClassNotFoundException) {
            Log.d(LOG_TAG, "ClassNotFoundException in decodeCookie", e)
        }
        return cookie
    }

    private fun byteArrayToHexString(bytes: ByteArray): String? {
        val sb = StringBuilder(bytes.size * 2)
        for (element in bytes) {
            val v: Int = (element and 0xff.toByte()).toInt()
            if (v < 16) {
                sb.append('0')
            }
            sb.append(Integer.toHexString(v))
        }
        return sb.toString().toUpperCase(Locale.US)
    }

    private fun hexStringToByteArray(hexString: String): ByteArray {
        val len = hexString.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(
                hexString[i],
                16
            ) shl 4) + Character.digit(hexString[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }

}