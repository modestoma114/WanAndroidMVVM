package me.robbin.wanandroid.ext

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import me.robbin.wanandroid.ui.adapter.ArticleAdapter

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

fun RecyclerView.setAdapter() {

    val adapter by lazy { ArticleAdapter() }

}
