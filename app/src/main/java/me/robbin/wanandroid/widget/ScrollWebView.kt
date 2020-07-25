package me.robbin.wanandroid.widget

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView

/**
 *
 * Create by Robbin at 2020/7/24
 */
class ScrollWebView(context: Context, attributeSet: AttributeSet) : WebView(context, attributeSet) {

    var onScrollChangeListener: OnScrollChangeListener? = null

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        onScrollChangeListener?.onScroll(l, t, oldl, oldt)
    }

    interface OnScrollChangeListener {
        fun onScroll(dx: Int, dy: Int, oldDx: Int, oldDy: Int)
    }

}