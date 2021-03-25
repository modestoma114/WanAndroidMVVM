package me.robbin.wanandroid.model

/**
 * 分类
 * Created by Robbin on 2020-07-13
 */
data class Chapter(
    private var children: MutableList<Chapter>,
    private var courseId: Int,
    private var id: Int,
    private var name: String,
    private var order: Int,
    private var parentChapterId: Int,
    private var userControlSetTop: Boolean,
    private var visible: Int
)