package me.robbin.wanandroid.ext

import android.view.View

/**
 *
 * Create by Robbin at 2020/7/10
 */
fun View.addTopPadding(padding: Int) {
    val lp = this.layoutParams
    if (lp != null && lp.height > 0) {
        lp.height += padding
    }
    this.setPadding(
        this.paddingLeft,
        this.paddingTop + padding,
        this.paddingRight,
        this.paddingBottom
    )
}
