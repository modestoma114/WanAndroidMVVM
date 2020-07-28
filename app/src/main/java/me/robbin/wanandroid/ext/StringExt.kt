package me.robbin.wanandroid.ext

import android.text.Html
import java.util.regex.Pattern

/**
 * String 拓展函数
 * Create by Robbin at 2020/7/12
 */

/**
 * 转化 HTML 格式的 String
 * Create by Robbin at 2020/7/12
 */
@Suppress("DEPRECATION")
fun String.html2string(): String {
    return Html.fromHtml(this).toString()
}

/**
 * 去除 String 当中的空格
 * @param count
 * Create by Robbin at 2020/7/12
 */
fun String.removeAllBlank(count: Int): String {
    val s: String
    val p =
        Pattern.compile("\\s{$count,}|\t|\r|\n")
    val m = p.matcher(this)
    s = m.replaceAll(" ")
    return s
}