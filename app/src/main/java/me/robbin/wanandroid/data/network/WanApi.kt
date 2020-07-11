package me.robbin.wanandroid.data.network

import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.data.bean.BannerBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * Create by Robbin at 2020/7/10
 */
interface WanApi {

    @GET("banner/json")
    suspend fun getBanner(): ApiResponse<List<BannerBean>>

    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): ApiResponse<ApiPageResponse<List<ArticleBean>>>

}