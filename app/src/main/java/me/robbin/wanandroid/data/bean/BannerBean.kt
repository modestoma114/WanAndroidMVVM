package me.robbin.wanandroid.data.bean

/**
 * Created by Robbin on 2020-07-10
 */
data class BannerBean(
    var desc: String,
    var id: Int,
    var imagePath: String,
    var isVisible: Int,
    var order: Int,
    var title: String,
    var type: Int,
    var url: String
)