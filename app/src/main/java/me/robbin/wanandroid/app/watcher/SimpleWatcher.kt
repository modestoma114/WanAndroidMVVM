package me.robbin.wanandroid.app.watcher

import android.text.Editable
import android.text.TextWatcher

/**
 * EditText 自定义 Watcher
 * Create by Robbin at 2020/7/17
 */
open class SimpleWatcher: TextWatcher {

    override fun afterTextChanged(s: Editable) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

}