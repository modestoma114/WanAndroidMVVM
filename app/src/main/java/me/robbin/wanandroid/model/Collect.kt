package me.robbin.wanandroid.model

/**
 * 收藏
 * Created by Robbin on 2020-07-26
 */
data class Collect(
    private var author: String,
    private var chapterId: String,
    private var chapterName: String,
    private var courseId: String,
    private var desc: String,
    private var envelopePic: String,
    private var id: String,
    private var link: String,
    private var niceDate: String,
    private var origin: String,
    private var originId: String,
    private var publishTime: Long,
    private var title: String,
    private var userId: String,
    private var visible: String,
    private var zan: String
)

data class CollectUrl(
    private var desc: String,
    private var icon: String,
    private var id: Int,
    private var link: String,
    private var name: String,
    private var order: Int,
    private var userId: Int,
    private var visible: Int
)