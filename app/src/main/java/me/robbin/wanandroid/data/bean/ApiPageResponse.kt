package me.robbin.wanandroid.data.bean

/**
 *
 * Create by Robbin at 2020/7/11
 */
data class ApiPageResponse<T>(
    var curPage: Int,
    var datas: T,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)