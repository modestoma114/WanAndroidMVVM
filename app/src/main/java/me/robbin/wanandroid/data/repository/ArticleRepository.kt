package me.robbin.wanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import me.robbin.wanandroid.data.datasource.*
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType

/**
 * 文章仓库
 * Create by Robbin at 2020/7/11
 */
class ArticleRepository private constructor() {

    companion object {
        val instance: ArticleRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ArticleRepository()
        }
    }

    /**
     * 获取首页文章列表
     * Create by Robbin at 2020/7/12
     */
    fun getHomeArticles() =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            HomeDataSource()
        }.flow

    /**
     * 根据 type 和 cid 获取文章列表
     * @param type 所要获取的文章类型
     * @param cid 所要获取的文章的模块编号
     * Create by Robbin at 2020/7/13
     */
    fun getArticles(type: ArticleType, cid: Int = -1) =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            ArticlesDataSource(type, cid)
        }.flow

    /**
     * 根据 query 查询文章
     * @param query 查询关键字
     * Create by Robbin at 2020/7/13
     */
    fun getSearchArticles(query: String) =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            SearchArticleDateSource(query)
        }.flow

    /**
     * 获取站内收藏文章列表
     * Create by Robbin at 2020/7/14
     */
    fun getCollectArticles() =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            CollectDataSource()
        }.flow

    /**
     * 获取收藏文章列表
     * Create by Robbin at 2020/7/14
     */
    fun getUserPage(userId: Int) =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            UserPageDataSource(userId)
        }.flow

    /**
     * 获取收藏文章列表
     * Create by Robbin at 2020/7/30
     */
    fun getMyShare() =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            MyShareDataSource()
        }.flow

}
