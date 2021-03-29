package me.robbin.wanandroid.model

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
    private var selfVisible: Boolean,
    private var shareDate: Long,
    private var shareUser: String,
    private var superChapterId: Int,
    private var superChapterName: String,
    private var tags: MutableList<Tags>,
    private var title: String,
    private var type: Int,
    private var userId: Int,
    private var visible: Int,
    private var zan: Int,
)