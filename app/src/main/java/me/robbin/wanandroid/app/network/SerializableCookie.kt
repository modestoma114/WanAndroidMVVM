package me.robbin.wanandroid.app.network

import okhttp3.Cookie
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable


/**
 *
 * Create by Robbin at 2020/7/18
 */
class SerializableCookie(
    @Transient private var cookie: Cookie
) : Serializable {

    @Transient
    private var clientCookie: Cookie? = null

    fun getCookie(): Cookie {
        var bestCookie = cookie
        if (this.clientCookie != null) {
            bestCookie = this.clientCookie!!
        }
        return bestCookie
    }

    /**
     * 将Cookie写到对象流中
     * Create by Robbin at 2020/7/18
     */
    @Throws(IOException::class)
    private fun writeObject(out: ObjectOutputStream) {
        out.writeObject(cookie.name())
        out.writeObject(cookie.value())
        out.writeLong(cookie.expiresAt())
        out.writeObject(cookie.domain())
        out.writeObject(cookie.path())
        out.writeBoolean(cookie.secure())
        out.writeBoolean(cookie.httpOnly())
        out.writeBoolean(cookie.hostOnly())
        out.writeBoolean(cookie.persistent())
    }

    /**
     * 从对象流中构建Cookie对象
     * Create by Robbin at 2020/7/18
     */
    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(input: ObjectInputStream) {
        val name: String = input.readObject() as String
        val value: String = input.readObject() as String
        val expiresAt: Long = input.readLong()
        val domain: String = input.readObject() as String
        val path: String = input.readObject() as String
        val secure: Boolean = input.readBoolean()
        val httpOnly: Boolean = input.readBoolean()
        val hostOnly: Boolean = input.readBoolean()

        var builder = Cookie.Builder()
            .name(name)
            .value(value)
            .expiresAt(expiresAt)
            .path(path)
        builder = if (hostOnly) builder.hostOnlyDomain(domain) else builder.domain(domain)
        builder = if (secure) builder.secure() else builder
        builder = if (httpOnly) builder.httpOnly() else builder
        clientCookie = builder.build()
    }

}