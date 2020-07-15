package me.robbin.wanandroid.data.api

import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.data.bean.BannerBean
import me.robbin.wanandroid.data.bean.ChapterBean
import me.robbin.wanandroid.data.bean.ApiPageResponse
import me.robbin.wanandroid.data.bean.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 * Create by Robbin at 2020/7/10
 */
interface WanApi {

    @GET("banner/json")
    suspend fun getBanner(): ApiResponse<List<BannerBean>>

    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int)
            : ApiResponse<ApiPageResponse<MutableList<ArticleBean>>>

    @GET("article/top/json")
    suspend fun getTopArticleList(): ApiResponse<List<ArticleBean>>

    @GET("wxarticle/chapters/json")
    suspend fun getPublicList(): ApiResponse<List<ChapterBean>>

    @GET("wxarticle/list/{cid}/{page}/json")
    suspend fun getPublicArticleList(
        @Path("cid") cid: Int,
        @Path("page") page: Int
    ): ApiResponse<ApiPageResponse<List<ArticleBean>>>

    @GET("wenda/list/{page}/json")
    suspend fun getQuestionArticleList(@Path("page") page: Int)
            : ApiResponse<ApiPageResponse<List<ArticleBean>>>

    @GET("user_article/list/{page}/json")
    suspend fun getUserArticleList(@Path("page") page: Int)
            : ApiResponse<ApiPageResponse<List<ArticleBean>>>

    @GET("tree/json")
    suspend fun getKnowledgeList(): ApiResponse<List<ChapterBean>>

    @GET("article/list/{page}/json")
    suspend fun getKnowledgeArticleList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): ApiResponse<ApiPageResponse<List<ArticleBean>>>

}