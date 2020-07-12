package me.robbin.wanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.data.network.ApiService

/**
 *
 * Create by Robbin at 2020/7/11
 */

class ArticleRepository {
    fun getArticle() = Pager(PagingConfig(pageSize = 20)) {
        ArticleDataSource()
    }.flow
}

class ArticleDataSource : PagingSource<Int, ArticleBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleBean> {

        return try {
            val page = params.key ?: -1
            val (data, curPage, pageCount) = ApiService.getArticle(page)
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if (curPage == pageCount) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

}