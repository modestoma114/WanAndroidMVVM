package me.robbin.wanandroid.model

/**
 * 搜索热词
 * Created by Robbin on 2020-07-19
 */
data class HotKey(
    private var id: Int,
    private var link: String,
    private var name: String,
    private var order: Int,
    private var visible: Int
)