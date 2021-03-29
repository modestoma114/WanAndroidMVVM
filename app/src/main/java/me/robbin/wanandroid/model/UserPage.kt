package me.robbin.wanandroid.model

/**
 * Created by maluq on 2020-07-29
 */
data class UserPage(
    private var coinInfo: Integral,
    private var shareArticles: ApiPageResponse<MutableList<Article>>
)