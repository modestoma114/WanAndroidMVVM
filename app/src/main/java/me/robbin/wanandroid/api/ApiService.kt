package me.robbin.wanandroid.api

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import me.robbin.wanandroid.app.network.NetworkClient
import me.robbin.wanandroid.model.ApiPageResponse
import me.robbin.wanandroid.model.ApiResponse
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
     * @param page    要获取第 page 页数据
     * @param success 返回获取的文章列表
     * Create by Robbin at 2020/7/12
     */
    suspend fun getHomeArticle(
        page: Int,
        success: (ApiResponse<ApiPageResponse<MutableList<ArticleBean>>>) -> Unit
    ) {
        coroutineScope {
            val banners = async { getApi().getBanners() }
            val top = async { getApi().getTopArticles() }
            val articles = async { getApi().getHomeArticles(page) }
            top.await().data[0].bannerList = banners.await().data
            articles.await().data.datas.addAll(0, top.await().data)
            success(articles.await())
        }
    }

}