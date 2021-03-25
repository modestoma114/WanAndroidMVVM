package me.robbin.wanandroid.model

import me.robbin.net.IBaseResponse

/**
 * 服务器返回数据基类
 * Create by Robbin at 2020/7/10
 */
data class ApiResponse<T>(val data: T, val errorCode: Int, val errorMsg: String) :
    IBaseResponse<T> {

    override fun code(): Int = errorCode

    override fun msg(): String = errorMsg

    override fun data(): T = data

    override fun isSuccess(): Boolean = errorCode == 0

}

data class ApiPageResponse<T>(
    var curPage: Int,
    var datas: T,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)