package me.robbin.wanandroid.app.ext

import android.view.View

/**
 * View 拓展函数
 * Create by Robbin at 2020/7/10
 */

/**
 * 给 View 添加顶部 Padding，大部分情况下用于 Toolbar
 * @param padding 要补充的边距
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
