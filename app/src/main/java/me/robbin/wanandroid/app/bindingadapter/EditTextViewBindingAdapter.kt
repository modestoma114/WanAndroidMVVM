package me.robbin.wanandroid.app.bindingadapter

import android.widget.EditText
import androidx.databinding.BindingAdapter
import me.robbin.wanandroid.app.watcher.SimpleWatcher

/**
 *
 * Create by Robbin at 2020/7/17
 */
@BindingAdapter("onTextChanged")
fun textChanged(view: EditText, watcher: SimpleWatcher) {
    view.addTextChangedListener(watcher)
}