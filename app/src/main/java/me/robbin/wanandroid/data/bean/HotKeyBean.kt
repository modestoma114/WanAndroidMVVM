package me.robbin.wanandroid.data.bean

/**
 * 搜索热词
 * Created by Robbin on 2020-07-19
 */
data class HotKeyBean(
    var id: Int,
    var link: String,
    var name: String,
    var order: Int,
    var visible: Int
) {
}