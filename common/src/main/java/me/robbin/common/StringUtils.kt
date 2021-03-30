package me.robbin.common

import androidx.core.text.HtmlCompat
import java.util.regex.Pattern

/**
 * 将HTML字符串转为普通字符串
 * @author Created by Robbin in 2021/03/30
 */
fun String?.fromHtml() =
    if (this.isNullOrEmpty()) ""
    else HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

/**
 * 去除String中的空格
 * @author Created by Robbin in 2021/03/30
 */
fun String.removeAllBlank(count: Int): String {
    val s: String
    val p =
        Pattern.compile("\\s{$count,}|\t|\r|\n")
    val m = p.matcher(this)
    s = m.replaceAll(" ")
    return s
}