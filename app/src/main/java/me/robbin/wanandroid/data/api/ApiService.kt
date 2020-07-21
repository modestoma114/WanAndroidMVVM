package me.robbin.wanandroid.data.api

import me.robbin.wanandroid.app.network.NetworkClient
import me.robbin.wanandroid.data.bean.ArticleBean

/**
 *
 * Create by Robbin at 2020/7/10
 */
object ApiService {

    fun getApi() =
        NetworkClient.instance.getApi("https://www.wanandroid.com", WanApi::class.java)

    suspend fun getArticle(page: Int): Triple<MutableList<ArticleBean>, Int, Int> {
        return if (page == -1) {
            val result = getApi().getTopArticles()
            Triple(result.data, 0, 1)
        } else {
            val result = getApi().getHomeArticles(page)
            Triple(result.data.datas, result.data.curPage, result.data.pageCount)
        }
    }

}