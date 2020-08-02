package me.robbin.wanandroid.api

import me.robbin.wanandroid.app.network.NetworkClient
import me.robbin.wanandroid.model.ArticleBean

/**
 * 获取 WanApi 实例
 * Create by Robbin at 2020/7/10
 */
object ApiService {

    fun getApi() =
        NetworkClient.instance.getApi("https://www.wanandroid.com", WanApi::class.java)

    /**
     * 获取首页文章列表数据
     * @param page 要获取第 page 页数据
     * @return 返回获取的文章列表
     * Create by Robbin at 2020/7/12
     */
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