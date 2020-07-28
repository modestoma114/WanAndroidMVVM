package me.robbin.wanandroid.app.network

import java.lang.Exception

/**
 * Paging 空数据错误
 * Create by Robbin at 2020/7/26
 */
class EmptyException(val code: String, val errMsg: String) : Exception()