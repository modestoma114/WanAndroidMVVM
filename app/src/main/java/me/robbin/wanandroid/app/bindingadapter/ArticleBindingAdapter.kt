package me.robbin.wanandroid.app.bindingadapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.api.load
import me.robbin.wanandroid.data.bean.TagsBean
import me.robbin.wanandroid.ext.html2string
import me.robbin.wanandroid.ext.removeAllBlank

/**
 *
 * Create by Robbin at 2020/7/21
 */
@BindingAdapter("imgIsVisible")
fun imgIsVisible(view: ImageView, url: String) {
    view.isVisible = url.isNotEmpty()
    if (url.isNotEmpty()) view.load(url)
}

@BindingAdapter("textAuthor", "textShareUser", requireAll = true)
fun textAuthor(view: TextView, author: String, shareUser: String) {
    view.text = if (author.isBlank()) shareUser else author
}

@BindingAdapter("isNew")
fun isNew(view: TextView, fresh: Boolean) {
    view.isVisible = fresh
}

@BindingAdapter("isTop")
fun isTop(view: TextView, type: Int) {
    view.isVisible = type == 1
}

@BindingAdapter("hasTag")
fun hasTag(view: TextView, tags: List<TagsBean>) {
    view.isVisible = tags.isNotEmpty()
    if (tags.isNotEmpty()) view.text = tags[0].name
}

@BindingAdapter("articleTitle")
fun articleTitle(view: TextView, title: String) {
    view.text = title.html2string()
}

@BindingAdapter("articleDesc")
fun articleDesc(view: TextView, desc: String) {
    view.isVisible = !TextUtils.isEmpty(desc)
    if (!TextUtils.isEmpty(desc)) view.text = desc.html2string().removeAllBlank(2)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("superChapter", "chapter", requireAll = true)
fun chapter(view: TextView, superChapter: String, chapter: String) {
    view.text = "${superChapter}·${chapter}"
}