package me.robbin.wanandroid.data.bean

/**
 * 积分排名
 * Created by Robbin on 2020-07-25
 */
data class IntegralDetailBean(
    var coinCount: Int,
    var date: Long,
    var desc: String,
    var id: Int,
    var reason: String,
    var type: Int,
    var userId: Int,
    var userName: String
)