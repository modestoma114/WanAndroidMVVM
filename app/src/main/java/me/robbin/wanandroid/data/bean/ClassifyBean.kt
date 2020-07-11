package me.robbin.wanandroid.data.bean

/**
 * Created by Robbin on 2020-07-10
 */
data class ClassifyBean(
    var courseId: Int,
    var id: Int,
    var name: String,
    var order: Int,
    var parentChapterId: Int,
    var userControlSetTop: Boolean,
    var visible: Int,
    var children: List<ClassifyBean>
)