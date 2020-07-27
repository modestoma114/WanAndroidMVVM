package me.robbin.wanandroid.app.bindingadapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 *
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