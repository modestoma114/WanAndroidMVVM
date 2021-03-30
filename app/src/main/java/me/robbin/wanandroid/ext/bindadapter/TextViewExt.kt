package me.robbin.wanandroid.ext.bindadapter

import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import me.robbin.common.fromHtml
import me.robbin.wanandroid.model.Tag

/**
 * 是否显示“新”标识
 * @param fresh 文章新发布标识
 * @author Created by Robbin in 2021/03/30
 */
@BindingAdapter("articleNew")
fun articleNew(view: TextView, fresh: Boolean) {
    view.isVisible = fresh
}

/**
 * 是否显示“置顶”标识
 * @param top 文章置顶标识
 * @author Created by Robbin in 2021/03/30
 */
@BindingAdapter("articleTop")
fun articleTop(view: TextView, top: Boolean) {
    view.isVisible = top
}

/**
 * 展示文章的标签
 * @param tag 文章的第一个标签
 * @author Created by Robbin in 2021/03/30
 */
@BindingAdapter("articleTag")
fun articleTag(view: TextView, tag: Tag?) {
    view.isVisible = tag != null
    if (tag != null) view.text = tag.tagName
}