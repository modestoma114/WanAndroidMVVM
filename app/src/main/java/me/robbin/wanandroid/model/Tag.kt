package me.robbin.wanandroid.model

import com.squareup.moshi.JsonClass

/**
 * 标签
 * Create by Robbin at 2020/7/11
 */
data class Tag(
    private var name: String,
    private var url: String
) {
    val tagName: String
        get() {
            return this.name
        }
}