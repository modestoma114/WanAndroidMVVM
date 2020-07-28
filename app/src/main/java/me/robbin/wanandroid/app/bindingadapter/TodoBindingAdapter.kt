package me.robbin.wanandroid.app.bindingadapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * 用于 Todo 列表的 BindingAdapter
 * Create by Robbin at 2020/7/27
 */

/**
 * 判断是否显示 Todo Detail
 * @param detail Todo Detail
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