package me.robbin.wanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import me.robbin.wanandroid.data.datasource.ArticlesDataSource
import me.robbin.wanandroid.data.datasource.CollectDataSource
import me.robbin.wanandroid.data.datasource.HomeDataSource
import me.robbin.wanandroid.data.datasource.SearchArticleDateSource
import me.robbin.wanandroid.ui.fragment.common.ArticleType

/**
 *
 * Create by Robbin at 2020/7/11
 */

class ArticleRepository private constructor() {

    companion object {
        val instance: ArticleRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ArticleRepository()
        }
    }

    fun getHomeArticles() =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            HomeDataSource()
        }.flow

    fun getArticles(type: ArticleType, cid: Int = -1) =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            ArticlesDataSource(type, cid)
        }.flow

    fun getSearchArticles(query: String) =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            SearchArticleDateSource(query)
        }.flow

    fun getCollectArticles() =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            CollectDataSource()
        }.flow

}
