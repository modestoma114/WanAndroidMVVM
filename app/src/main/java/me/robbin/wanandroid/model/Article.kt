package me.robbin.wanandroid.model

import com.squareup.moshi.JsonClass
import me.robbin.common.fromHtml
import me.robbin.common.removeAllBlank

/**
 * 文章
 * Created by Robbin on 2020-07-11
 */
data class Article(
    private var apkLink: String,
    private var audit: Int,
    private var author: String,
    private var canEdit: Boolean,
    private var chapterId: Int,
    private var chapterName: String,
    private var collect: Boolean,
    private var courseId: Int,
    private var desc: String,
    private var descMd: String,
    private var envelopePic: String,
    private var fresh: Boolean,
    private var host: String,
    private var id: Int,
    private var link: String,
    private var niceDate: String,
    private var niceShareDate: String,
    private var origin: String,
    private var prefix: String,
    private var projectLink: String,
    private var publishTime: Long,
    private var realSuperChapterId: Int,
    private var selfVisible: Int,
    private var shareDate: Long,
    private var shareUser: String,
    private var superChapterId: Int,
    private var superChapterName: String,
    private var tags: MutableList<Tag>,
    private var title: String,
    private var type: Int,
    private var userId: Int,
    private var visible: Int,
    private var zan: Int,
) {
    val realAuthor: String
        get() {
            if (author.isBlank() && shareUser.isBlank()) return "匿名"
            return if (author.isBlank()) shareUser else author
        }

    val projectPic: String
        get() {
            return this.envelopePic
        }

    val newTag: Boolean
        get() {
            return this.fresh
        }

    val topTag: Boolean
        get() {
            return this.type == 1
        }

    val tag: Tag?
        get() {
            return if (tags.isEmpty()) null else tags[0]
        }

    val date: String
        get() {
            return this.niceDate
        }

    val realTitle: String
        get() {
            return this.title.fromHtml()
        }

    val realDesc: String
        get() {
            return this.desc.fromHtml().removeAllBlank(2)
        }

    val realChapter: String
        get() {
            return "${this.superChapterName}·${this.chapterName}"
        }

    var haveCollect: Boolean
        get() {
            return this.collect
        }
        set(value) {
            this.collect = value
        }

    fun sameId(article: Article): Boolean = this.id == article.id

    fun sameContent(article: Article): Boolean = this == article

}