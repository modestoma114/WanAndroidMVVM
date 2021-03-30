package me.robbin.wanandroid.api

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import me.robbin.wanandroid.model.ApiPageResponse
import me.robbin.wanandroid.model.ApiResponse
import me.robbin.wanandroid.model.Article
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * 获取 WanApi 实例
 * Create by Robbin at 2020/7/10
 */
object WanService {

    fun getApi(): WanApi {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WanApi::class.java)
    }

//    fun getApi() =
//        NetworkClient.instance.getApi("https://www.wanandroid.com", WanApi::class.java)

    /**
     * 获取首页文章列表数据
     * @param page    要获取第 page 页数据
     * @param success 返回获取的文章列表
     * Create by Robbin at 2020/7/12
     */
//    suspend fun getHomeArticle(
//        page: Int,
//        success: (ApiResponse<ApiPageResponse<MutableList<Article>>>) -> Unit
//    ) {
//        coroutineScope {
//            val banners = async { getApi().getBanners() }
//            val top = async { getApi().getTopArticles() }
//            val articles = async { getApi().getHomeArticles(page) }
//            val temp = top.await().data[0]
//            temp.bannerList = banners.await().data
//            top.await().data.add(0, temp)
//            articles.await().data.datas.addAll(0, top.await().data)
//            success(articles.await())
//        }
//    }

}