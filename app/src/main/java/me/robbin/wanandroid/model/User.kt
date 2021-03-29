package me.robbin.wanandroid.model

/**
 * 用户信息 Model
 * Create by Robbin at 2020/7/10
 */
data class User(
    private var admin: Boolean,
    private var chapterTops: MutableList<String>,
    private var coinCount: Int,
    private var collectIds: MutableList<String>,
    private var email: String,
    private var icon: String,
    private var id: String,
    private var nickname: String,
    private var password: String,
    private var publicName: String,
    private var token: String,
    private var type: String,
    private var username: String
)