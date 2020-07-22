package me.robbin.wanandroid.app.bindingadapter

import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import me.robbin.wanandroid.app.watcher.SimpleWatcher

/**
 *
 * Create by Robbin at 2020/7/17
 */
@BindingAdapter("onTextChanged")
fun textChanged(view: EditText, watcher: SimpleWatcher) {
    view.addTextChangedListener(watcher)
}

@BindingAdapter("loadImg")
fun loadImg(view: ImageView, url: String) {
    view.load(url)
}