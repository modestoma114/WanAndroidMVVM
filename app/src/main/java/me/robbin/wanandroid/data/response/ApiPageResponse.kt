package me.robbin.wanandroid.data.response

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
) {

    fun isEmpty(): Boolean {
        return (datas as List<*>).size == 0
    }

    fun isRefresh(): Boolean {
        return offset == 0
    }

    fun hasMore(): Boolean {
        return !over
    }

}