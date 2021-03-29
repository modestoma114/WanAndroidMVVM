package me.robbin.wanandroid.api

import me.robbin.wanandroid.model.*
import retrofit2.http.*

/**
 * WanAndroid Api
 * Create by Robbin at 2020/7/10
 */
interface WanApi {

    // ******************** 用户相关 ********************
    /**
     * 用户登录
     * <br>Created by Robbin in 2020/7/10
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): ApiResponse<User>

    /**
     * 用户注册
     * <br>Created by Robbin in 2020/7/10
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): ApiResponse<User>

    /**
     * 用户注销
     * <br>Created by Robbin in 2021/3/29
     */
    @GET("user/logout/json")
    suspend fun logout(): ApiResponse<Any?>

    // ******************** 收藏相关 ********************
    /**
     * 站内收藏文章列表
     * Created by Robbin in 2020/7/30
     */
    @GET("lg/collect/list/{page}/json")
    suspend fun collectArticles(
        @Path("page") page: Int
    ): ApiResponse<ApiPageResponse<MutableList<Collect>>>

    /**
     * 收藏网址列表
     * Created by Robbin in 2020/7/30
     */
    @GET("lg/collect/usertools/json")
    suspend fun userCollectArticles(): ApiResponse<MutableList<CollectUrl>>

    /**
     * 收藏站内文章
     * Created by Robbin in 2020/7/31
     */
    @POST("lg/collect/{id}/json")
    suspend fun collect(
        @Path("id") id: Int
    ): ApiResponse<Any?>

    /**
     * 收藏站外文章
     * Created by Robbin in 2020/7/31
     */
    @POST("lg/collect/add/json")
    suspend fun collectCustom(
        @Query("title") title: String,
        @Query("author") author: String,
        @Query("link") link: String
    ): ApiResponse<Collect>

    /**
     * 收藏网址
     * Created by Robbin in 2021/3/29
     */
    @POST("lg/collect/addtool/json")
    suspend fun collectUrl(
        @Query("name") name: String,
        @Query("link") link: String
    ): ApiResponse<CollectUrl>

    /**
     * 编辑收藏网址
     * Created by Robbin in 2021/3/29
     */
    @POST("lg/collect/updatetool/json")
    suspend fun editCollectUrl(
        @Query("id") id: Int,
        @Query("name") name: String,
        @Query("link") link: String
    )

    /**
     * 根据文章 ID 取消收藏
     * Created by Robbin in 2020/7/31
     */
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun uncollect(
        @Path("id") id: Int
    ): ApiResponse<Any?>

    /**
     * 根据收藏 ID 取消收藏
     * Created by Robbin in 2020/7/31
     */
    @POST("lg/uncollect/{id}/json")
    suspend fun uncollectCustom(
        @Path("id") id: Int,
        @Query("originId") originId: Int
    ): ApiResponse<Any?>

    /**
     * 根据 ID 取消收藏网址
     * Created by Robbin in 2021/3/29
     */
    @POST("lg/collect/deletetool/json")
    suspend fun uncollectUrl(
        @Query("id") id: Int
    ): ApiResponse<Any?>

    // ******************** 首页相关 ********************
    /**
     * 首页文章列表
     * Created by Robbin in 2021/3/29
     */
    @GET("article/list/{page}/json")
    suspend fun homeArticles(
        @Path("page") page: Int
    ): ApiResponse<ApiPageResponse<MutableList<Article>>>

    /**
     * 置顶文章列表
     * Created by Robbin in 2021/3/29
     */
    @GET("article/top/json")
    suspend fun topArticles(): ApiResponse<MutableList<Article>>

    /**
     * 首页 Banner 列表
     * Created by Robbin in 2021/3/29
     */
    @GET("banner/json")
    suspend fun banners(): ApiResponse<MutableList<Banner>>

    /**
     * 常用网站列表
     * Created by Robbin in 2021/3/29
     */
    @GET("friend/json")
    suspend fun friendSite(): ApiResponse<MutableList<FriendSite>>

    /**
     * 搜素热词
     * Created by Robbin in 2021/3/29
     */
    @GET("hotkey/json")
    suspend fun hotKey(): ApiResponse<MutableList<HotKey>>

    // ******************** 知识体系相关 ********************
    /**
     * 知识体系列表
     * Created by Robbin in 2021/3/29
     */
    @GET("tree/json")
    suspend fun tree(): ApiResponse<MutableList<Chapter>>

    /**
     * 知识体系文章列表
     * Created by Robbin in 2021/3/29
     */
    @GET("article/list/{page}/json")
    suspend fun treeArticles(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): ApiResponse<ApiPageResponse<MutableList<Article>>>

    /**
     * 根据作者昵称获取文章列表
     * Created by Robbin in 2021/3/29
     */
    @GET("article/list/{page}/json")
    suspend fun authorArticles(
        @Path("page") page: Int,
        @Query("author") author: String
    ): ApiResponse<ApiPageResponse<Article>>

    // ******************** 导航相关 ********************
    /**
     * 导航数据
     * Created by Robbin in 2021/3/29
     */
    @GET("navi/json")
    suspend fun navigations(): ApiResponse<MutableList<Navigation>>

    // ******************** 项目相关 ********************
    /**
     * 项目分类
     * Created by Robbin in 2021/3/29
     */
    @GET("project/tree/json")
    suspend fun projectTree(): ApiResponse<MutableList<Chapter>>

    /**
     * 特定项目文章列表
     */
    @GET("project/list/{page}/json")
    suspend fun projects(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): ApiResponse<ApiPageResponse<MutableList<Article>>>

    // ******************** 搜索相关 ********************
    /**
     * 根据关键词搜索
     * Created by Robbin in 2021/3/29
     */
    @GET("article/query/{page}/json")
    suspend fun search(
        @Path("page") page: Int,
        @Query("k") k: String
    ): ApiResponse<ApiPageResponse<MutableList<Article>>>

    // ******************** 积分相关 ********************
    /**
     * 获取积分排行榜，page 从 1 开始
     * Created by Robbin in 2021/3/29
     */
    @GET("coin/rank/{page}/json")
    suspend fun coinRank(
        @Path("page") page: Int
    ): ApiResponse<ApiPageResponse<MutableList<Integral>>>

    /**
     * 个人积分详细
     * Created by Robbin in 2021/3/29
     */
    @GET("lg/coin/userinfo/json")
    suspend fun myCoin(): ApiResponse<Integral>

    /**
     * 获取个人积分获取详情
     * Created by Robbin in 2021/3/29
     */
    @GET("lg/coin/list/{page}/json")
    suspend fun coinList(
        @Path("page") page: Int
    ): ApiResponse<ApiPageResponse<MutableList<Integral>>>

    // ******************** 广场相关 ********************
    /**
     * 广场文章列表
     * Created by Robbin in 2021/3/29
     */
    @GET("user_article/list/{page}/json")
    suspend fun userArticles(
        @Path("page") page: Int
    ): ApiResponse<ApiPageResponse<MutableList<Article>>>

    /**
     * 根据用户 ID 获取分享文章列表，page 从 1 开始
     * Created by Robbin in 2021/3/29
     */
    @GET("user/{id}/articles/{page}/json")
    suspend fun userArticles(
        @Path("id") id: Int,
        @Path("page") page: Int
    ): ApiResponse<ApiPageResponse<MutableList<UserPage>>>

    /**
     * 获取自己分享的文章
     * Created by Robbin in 2021/3/29
     */
    @GET("user/lg/private_articles/{page}/json")
    suspend fun myArticles(
        @Path("page") page: Int
    ): ApiResponse<ApiPageResponse<MutableList<Article>>>

    /**
     * 删除自己的文章
     * Created by Robbin in 2021/3/29
     */
    @POST("lg/user_article/delete/{id}/json")
    suspend fun deleteMyArticle(
        @Path("id") id: Int
    ): ApiResponse<Any?>

    /**
     * 分享文章
     * Created by Robbin in 2021/3/29
     */
    @POST("lg/user_article/add/json")
    suspend fun shareArticle(
        @Query("title") title: String,
        @Query("link") link: String
    ): ApiResponse<Article>

    // ******************** 问答相关 ********************
    @GET("wenda/list/{page}/json")
    suspend fun question(
        @Path("page") page: Int
    ): ApiResponse<ApiPageResponse<MutableList<Article>>>

}