package me.robbin.wanandroid.model

/**
 * 收藏
 * Created by Robbin on 2020-07-26
 */
data class Collect(
    var author: String,
    var chapterId: Int,
    var chapterName: String,
    var courseId: Int,
    var desc: String,
    var envelopePic: String,
    var id: Int,
    var link: String,
    var niceDate: String,
    var origin: String,
    var originId: Int,
    var publishTime: Long,
    var title: String,
    var userId: Int,
    var visible: Int,
    var zan: Int
)