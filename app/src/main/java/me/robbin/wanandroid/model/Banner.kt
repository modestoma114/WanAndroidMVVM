package me.robbin.wanandroid.model

/**
 * Created by Robbin on 2020-08-03
 */
data class Banner(
    private var desc: String,
    private var id: Int,
    private var imagePath: String,
    private var isVisible: Int,
    private var order: Int,
    private var title: String,
    private var type: Int,
    private var url: String
)