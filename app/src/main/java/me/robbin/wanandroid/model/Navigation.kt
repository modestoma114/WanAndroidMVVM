package me.robbin.wanandroid.model

/**
 * 导航
 * Created by Robbin on 2020-07-21
 */
data class Navigation(
    private var articles: MutableList<Article>,
    private var cid: Int,
    private var name: String
)