package me.robbin.wanandroid.ext.bindadapter

import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load

/**
 * 根据传入的Url判断是否显示ImageView
 * @param url 进行判断的Url
 * @author Created by Robbin in 2021/03/30
 */
@BindingAdapter("imgVisible")
fun imgVisible(view: ImageView, url: String) {
    view.isVisible = url.isNotEmpty()
    if (view.isVisible) view.load(uri = url)
}