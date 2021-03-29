package me.robbin.wanandroid.model

/**
 * 积分
 * Created by Robbin on 2020-07-19
 */
data class Integral(
    private var coinCount: Int,
    private var level: Int,
    private var nickName: String,
    private var rank: Int,
    private var userId: Int,
    private var username: String
)

data class IntegralDetail(
    private var coinCount: Int,
    private var date: Long,
    private var desc: String,
    private var id: Int,
    private var reason: String,
    private var type: Int,
    private var userId: Int,
    private var userName: String
)