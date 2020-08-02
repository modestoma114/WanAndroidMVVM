package me.robbin.wanandroid.model

import me.robbin.mvvmscaffold.network.IBaseResponse

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