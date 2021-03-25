package me.robbin.wanandroid.model

/**
 * Created by maluq on 2020-07-29
 */
data class UserPageBean(
    var coinInfo: IntegralBean,
    var shareArticles: ApiPageResponse<MutableList<Article>>
)