package me.robbin.wanandroid.app.ext.bindingadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import me.robbin.wanandroid.R

/**
 * 用于 TodoL 列表的 BindingAdapter
 * Create by Robbin at 2020/7/27
 */

/**
 * 判断是否显示 TodoL Detail
 * @param detail TodoL Detail
 * Create by Robbin at 2020/7/27
 */
@BindingAdapter("todoDetail")
fun todoDetail(view: TextView, detail: String) {
    if (detail.isBlank()) {
        view.visibility = View.GONE
    } else {
        view.text = detail
    }
}

@BindingAdapter("todoIcon")
fun todoIcon(view: ImageView, status: Int) {
    if (status == 0) {
        view.setImageResource(R.drawable.ic_done)
    } else {
        view.setImageResource(R.drawable.ic_redo)
    }
}