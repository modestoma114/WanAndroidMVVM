package me.robbin.wanandroid.app.ext.bindingadapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import me.robbin.wanandroid.model.TagsBean
import me.robbin.wanandroid.app.ext.html2string
import me.robbin.wanandroid.app.ext.removeAllBlank

/**
 * 用于 Article 列表的 BindingAdapter
 * Create by Robbin at 2020/7/21
 */

/**
 * 判断当前 Item 中的 ImageView 是否显示，url 为空，不显示
 * @param url 文章封面图链接
 * Create by Robbin at 2020/7/21
 */
@BindingAdapter("imgIsVisible")
fun imgIsVisible(view: ImageView, url: String) {
    view.isVisible = url.isNotEmpty()
    if (url.isNotEmpty()) view.load(url)
}

/**
 * 判断当前 Item 的用户名显示情况
 * @param author 文章作者
 * @param shareUser 文章分享者
 * Create by Robbin at 2020/7/21
 */
@BindingAdapter("articleAuthor", "articleShareUser", requireAll = true)
fun articleAuthor(view: TextView, author: String, shareUser: String) {
    view.text = if (author.isBlank()) shareUser else author
}

@BindingAdapter("collectAuthor")
fun collectAuthor(view: TextView, author: String) {
    view.text = if (author.isBlank()) author else "匿名"
}

/**
 * 是否显示 “新” 标识
 * @param fresh 文章新发布标识
 * Create by Robbin at 2020/7/21
 */
@BindingAdapter("articleNew")
fun articleNew(view: TextView, fresh: Boolean) {
    view.isVisible = fresh
}

/**
 * 是否显示 “置顶” 标识
 * @param type 文章类型
 * Create by Robbin at 2020/7/21
 */
@BindingAdapter("articleTop")
fun articleTop(view: TextView, type: Int) {
    view.isVisible = type == 1
}

/**
 * 是否显示 Tag
 * @param tags 当前文章 Tag 列表
 * Create by Robbin at 2020/7/21
 */
@BindingAdapter("articleTag")
fun articleTag(view: TextView, tags: List<TagsBean>) {
    view.isVisible = tags.isNotEmpty()
    if (tags.isNotEmpty()) view.text = tags[0].name
}

/**
 * 处理文章标题
 * @param title 当前文章标题
 * Create by Robbin at 2020/7/21
 */
@BindingAdapter("articleTitle")
fun articleTitle(view: TextView, title: String) {
    view.text = title.html2string()
}

/**
 * 处理文章简介
 * @param desc 当前文章简介
 * Create by Robbin at 2020/7/21
 */
@BindingAdapter("articleDesc")
fun articleDesc(view: TextView, desc: String) {
    view.isVisible = !TextUtils.isEmpty(desc)
    if (!TextUtils.isEmpty(desc)) view.text = desc.html2string().removeAllBlank(2)
}

/**
 * 处理当前文章所属分类
 * @param superChapter 文章所属模块
 * @param chapter 文章所属分类
 * Create by Robbin at 2020/7/21
 */
@SuppressLint("SetTextI18n")
@BindingAdapter("superChapter", "chapter", requireAll = true)
fun chapter(view: TextView, superChapter: String, chapter: String) {
    view.text = "${superChapter}·${chapter}"
}