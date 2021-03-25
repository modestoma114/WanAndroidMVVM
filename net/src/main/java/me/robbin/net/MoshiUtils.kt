package me.robbin.net

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.internal.Util
import okio.Buffer
import okio.BufferedSource
import java.io.InputStream
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

object MoshiUtils {

    val client: Moshi = Moshi.Builder().build()

    fun <T> fromJson(json: String, type: Type): T? = getAdapter<T>(type).fromJson(json)
    fun <T> fromJson(buffer: BufferedSource, type: Type): T? = getAdapter<T>(type).fromJson(buffer)
    fun <T> fromJson(`is`: InputStream, type: Type): T? = getAdapter<T>(type).fromJson(Buffer().readFrom(`is`))
    fun <T> fromJson(reader: JsonReader, type: Type): T? = getAdapter<T>(type).fromJson(reader)

    inline fun <reified T> fromJson(json: String): T? = getAdapter<T>().fromJson(json)
    inline fun <reified T> fromJson(buffer: BufferedSource): T? = getAdapter<T>().fromJson(buffer)
    inline fun <reified T> fromJson(`is`: InputStream): T? = getAdapter<T>().fromJson(Buffer().readFrom(`is`))
    inline fun <reified T> fromJson(reader: JsonReader): T? = getAdapter<T>().fromJson(reader)

    inline fun <reified T> listFromJson(json: String): MutableList<T> =
        fromJson(
            json = json,
            type = Types.newParameterizedType(MutableList::class.java, T::class.java)
        ) ?: mutableListOf()
    inline fun <reified T> listFromJson(buffer: BufferedSource): MutableList<T> =
        fromJson(
            buffer = buffer,
            type = Types.newParameterizedType(MutableList::class.java, T::class.java)
        ) ?: mutableListOf()
    inline fun <reified T> listFromJson(`is`: InputStream): MutableList<T> =
        fromJson(
            `is` = `is`,
            type = Types.newParameterizedType(MutableList::class.java, T::class.java)
        ) ?: mutableListOf()
    inline fun <reified T> listFromJson(reader: JsonReader): MutableList<T> =
        fromJson(
            reader = reader,
            type = Types.newParameterizedType(MutableList::class.java, T::class.java)
        ) ?: mutableListOf()

    inline fun <reified K, reified V> mapFromJson(json: String): MutableMap<K, V> =
        fromJson(
            json = json,
            type = Types.newParameterizedType(MutableMap::class.java, K::class.java, V::class.java)
        ) ?: mutableMapOf()
    inline fun <reified K, reified V> mapFromJson(buffer: BufferedSource): MutableMap<K, V> =
        fromJson(
            buffer = buffer,
            type = Types.newParameterizedType(MutableMap::class.java, K::class.java, V::class.java)
        ) ?: mutableMapOf()
    inline fun <reified K, reified V> mapFromJson(`is`: InputStream): MutableMap<K, V> =
        fromJson(
            `is` = `is`,
            type = Types.newParameterizedType(MutableMap::class.java, K::class.java, V::class.java)
        ) ?: mutableMapOf()
    inline fun <reified K, reified V> mapFromJson(reader: JsonReader): MutableMap<K, V> =
        fromJson(
            reader = reader,
            type = Types.newParameterizedType(MutableMap::class.java, K::class.java, V::class.java)
        ) ?: mutableMapOf()

    inline fun <reified T> toJson(t: T) = getAdapter<T>().toJson(t) ?: ""

    fun <T> getAdapter(type: Type): JsonAdapter<T> = client.adapter(type)
    inline fun <reified T> getAdapter(): JsonAdapter<T> =
        client.adapter(object : TypeToken<T>() {}.type)

}

abstract class TypeToken<T> {
    val type: Type
        get() = run {
            val superClass = javaClass.genericSuperclass
            Util.canonicalize((superClass as ParameterizedType).actualTypeArguments[0])
        }
}

inline fun <reified T: Any> String.fromJson() = MoshiUtils.fromJson<T>(this)
fun Any.toJson() = MoshiUtils.toJson(this)