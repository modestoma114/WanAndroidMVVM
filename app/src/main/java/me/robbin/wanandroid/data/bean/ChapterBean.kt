package me.robbin.wanandroid.data.bean

/**
 * Created by Robbin on 2020-07-13
 */
data class ChapterBean(
    var courseId: Int,
    var id: Int,
    var name: String,
    var order: Int,
    var parentChapterId: Int,
    var userControlSetTop: Boolean,
    var visible: Int,
    var children: List<ChapterBean>
)