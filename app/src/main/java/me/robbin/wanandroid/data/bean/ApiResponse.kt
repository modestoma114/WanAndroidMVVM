package me.robbin.wanandroid.data.bean

import me.robbin.mvvmscaffold.network.IBaseResponse

/**
 * 服务器返回数据基类
 * Create by Robbin at 2020/7/10
 */
data class ApiResponse<T>(val code: Int, val msg: String, val data: T): IBaseResponse<T> {

    override fun code(): Int = code

    override fun msg(): String = msg

    override fun data(): T = data

    override fun isSuccess(): Boolean = code == 0

}