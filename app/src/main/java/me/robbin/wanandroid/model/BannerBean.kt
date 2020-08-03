package me.robbin.wanandroid.model

/**
 * Created by Robbin on 2020-08-03
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