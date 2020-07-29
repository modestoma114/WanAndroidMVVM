package me.robbin.wanandroid.data.api

import me.robbin.wanandroid.data.bean.*
import retrofit2.http.*

/**
 * WanAndroid Api
 * Create by Robbin at 2020/7/10
 */
interface WanApi {

    /**
     * 获取首页文章列表
     * Create by Robbin at 2020/7/10
     */
    @GET("article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int)
            : ApiResponse<ApiPageResponse<MutableList<ArticleBean>>>

    /**
     * 获取首页置顶文章列表
     * Create by Robbin at 2020/7/10
     */
    @GET("article/top/json")
    suspend fun getTopArticles(): ApiResponse<MutableList<ArticleBean>>

    /**
     * 获取公众号列表
     * Create by Robbin at 2020/7/10
     */
    @GET("wxarticle/chapters/json")
    suspend fun getPublicList(): ApiResponse<MutableList<ChapterBean>>

    /**
     * 根据cid获取公众号文章列表
     * Create by Robbin at 2020/7/10
     */
    @GET("wxarticle/list/{cid}/{page}/json")
    suspend fun getPublicArticles(
        @Path("cid") cid: Int,
        @Path("page") page: Int
    ): ApiResponse<ApiPageResponse<MutableList<ArticleBean>>>

    /**
     * 获取问答文章列表
     * Create by Robbin at 2020/7/10
     */
    @GET("wenda/list/{page}/json")
    suspend fun getQuestionArticles(@Path("page") page: Int)
            : ApiResponse<ApiPageResponse<MutableList<ArticleBean>>>

    /**
     * 获取广场文章列表
     * Create by Robbin at 2020/7/10
     */
    @GET("user_article/list/{page}/json")
    suspend fun getShareArticles(@Path("page") page: Int)
            : ApiResponse<ApiPageResponse<MutableList<ArticleBean>>>

    /**
     * 获取知识体系分类列表
     * Create by Robbin at 2020/7/10
     */
    @GET("tree/json")
    suspend fun getKnowledgeList(): ApiResponse<MutableList<ChapterBean>>

    /**
     * 根据cid获取知识体系文章列表
     * Create by Robbin at 2020/7/10
     */
    @GET("article/list/{page}/json")
    suspend fun getKnowledgeArticles(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): ApiResponse<ApiPageResponse<MutableList<ArticleBean>>>

    /**
     * 获取项目分类列表
     * Create by Robbin at 2020/7/10
     */
    @GET("project/tree/json")
    suspend fun getProjectList(): ApiResponse<MutableList<ChapterBean>>

    /**
     * 根据cid获取项目文章列表
     * Create by Robbin at 2020/7/10
     */
    @GET("project/list/{page}/json")
    suspend fun getProjectArticles(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): ApiResponse<ApiPageResponse<MutableList<ArticleBean>>>

    /**
     * 获取最新项目文章列表
     * Create by Robbin at 2020/7/10
     */
    @GET("article/listproject/{page}/json")
    suspend fun getLastProjectArticles(@Path("page") page: Int)
            : ApiResponse<ApiPageResponse<MutableList<ArticleBean>>>

    @GET("navi/json")
    suspend fun getNaviList(): ApiResponse<MutableList<NavigationBean>>

    /**
     * 获取搜索热词
     * Create by Robbin at 2020/7/10
     */
    @GET("hotkey/json")
    suspend fun getHotKey(): ApiResponse<MutableList<HotKeyBean>>

    /**
     * 根据关键词搜索
     * Create by Robbin at 2020/7/10
     */
    @POST("article/query/{page}/json")
    suspend fun getSearchDataByKey(
        @Path("page") pageNo: Int,
        @Query("k") searchKey: String
    ): ApiResponse<ApiPageResponse<MutableList<ArticleBean>>>

    /**
     * 登录
     * Create by Robbin at 2020/7/10
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") pwd: String
    ): ApiResponse<UserBean>

    // Integral

    /**
     * 注册
     * Create by Robbin at 2020/7/10
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") pwd: String,
        @Field("repassword") pwd2: String
    ): ApiResponse<UserBean>

    /**
     * 获取用户积分
     * Create by Robbin at 2020/7/10
     */
    @GET("lg/coin/userinfo/json")
    suspend fun getIntegral(): ApiResponse<IntegralBean>

    /**
     * 获取积分详细
     * Create by Robbin at 2020/7/25
     */
    @GET("lg/coin/list/{page}/json")
    suspend fun getIntegralDetail(@Path("page") page: Int)
            : ApiResponse<ApiPageResponse<MutableList<IntegralDetailBean>>>

    @GET("coin/rank/{page}/json")
    suspend fun getIntegralRank(@Path("page") page: Int)
            : ApiResponse<ApiPageResponse<MutableList<IntegralBean>>>

    // Collect

    @GET("lg/collect/list/{page}/json")
    suspend fun getCollectArticles(@Path("page") page: Int)
            : ApiResponse<ApiPageResponse<MutableList<CollectBean>>>

    // Share

    @GET("user/lg/private_articles/{page}/json")
    suspend fun getMyShare(@Path("page") page: Int)
            : ApiResponse<ApiPageResponse<MutableList<ArticleBean>>>

    // TodoL

    @GET("lg/todo/v2/list/{page}/json")
    suspend fun getTodoList(@Path("page") page: Int)
            : ApiResponse<ApiPageResponse<MutableList<TodoBean>>>

}