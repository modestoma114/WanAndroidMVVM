package me.robbin.wanandroid.app.bindingadapter

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.api.load
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.wanandroid.app.watcher.SimpleWatcher
import me.robbin.wanandroid.ext.addTopPadding

/**
 *
 * Create by Robbin at 2020/7/17
 */
@BindingAdapter("addStatusPadding")
fun addStatusPadding(view: View, flag: Boolean) {
    if (flag) view.addTopPadding(StatusBarUtils.getStatusBarHeight())
}

@BindingAdapter("onTextChanged")
fun textChanged(view: EditText, watcher: SimpleWatcher) {
    view.addTextChangedListener(watcher)
}

@BindingAdapter("loadImg")
fun loadImg(view: ImageView, url: String) {
    view.load(url)
}

@BindingAdapter("isVisible")
fun isVisible(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}

@BindingAdapter("tbNavIconClick")
fun tbNavIconClick(view: Toolbar, action: () -> Unit) {
    view.setNavigationOnClickListener { action() }
}