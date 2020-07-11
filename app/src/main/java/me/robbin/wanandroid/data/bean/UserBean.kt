package me.robbin.wanandroid.data.bean

/**
 *
 * Create by Robbin at 2020/7/10
 */
data class UserBean(
    var admin: Boolean,
    var chapterTops: List<String>,
    var collectIds: MutableList<String>,
    var email: String = "",
    var icon: String = "",
    var id: String = "",
    var nickname: String = "",
    var password: String = "",
    var token: String = "",
    var type: Int = 0,
    var username: String = ""
)