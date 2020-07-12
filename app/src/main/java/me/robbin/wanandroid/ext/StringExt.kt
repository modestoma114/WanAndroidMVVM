package me.robbin.wanandroid.ext

import android.text.Html
import java.util.regex.Pattern

/**
 *
 * Create by Robbin at 2020/7/12
 */
fun String.html2string(): String {
    return Html.fromHtml(this).toString()
}

fun String.removeAllBlank(count: Int):String {
    var s = ""
    if (this != null) {
        val p =
            Pattern.compile("\\s{$count,}|\t|\r|\n")
        val m = p.matcher(this)
        s = m.replaceAll(" ")
    }
    return s!!
}