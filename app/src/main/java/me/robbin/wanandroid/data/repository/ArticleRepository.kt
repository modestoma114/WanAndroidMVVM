package me.robbin.wanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import me.robbin.mvvmscaffold.network.RetrofitClient
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
            val page = params.key ?: 0
            val result = ApiService.getApi().getArticleList(page)
            LoadResult.Page(
                data = result.data.datas,
                prevKey = null,
                nextKey = if (result.data.curPage == result.data.pageCount) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

}