package me.robbin.wanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import me.robbin.wanandroid.data.datasource.ArticleDataSource
import me.robbin.wanandroid.data.datasource.ChapterArticleDataSource
import me.robbin.wanandroid.data.datasource.PublicArticleDataSource

/**
 *
 * Create by Robbin at 2020/7/11
 */

class ArticleRepository {

    companion object {
        val instance: ArticleRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ArticleRepository()
        }
    }

    fun getArticle() =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            ArticleDataSource()
        }.flow

    fun getPublicArticle(cid: Int) =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            PublicArticleDataSource(cid)
        }.flow

    fun getArticleList(type: Int, cid: Int = -1) =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            ChapterArticleDataSource(type, cid)
        }.flow

    fun getQuestionList(type: Int) =
        Pager(PagingConfig(pageSize = 22, enablePlaceholders = false)) {
            ChapterArticleDataSource(type)
        }

}
