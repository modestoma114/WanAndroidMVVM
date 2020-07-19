package me.robbin.wanandroid.data.datasource

import androidx.paging.PagingSource
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.ArticleBean
import retrofit2.HttpException
import java.io.IOException

/**
 *
 * Create by Robbin at 2020/7/19
 */
class SearchArticleDateSource(private val query: String) : PagingSource<Int, ArticleBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleBean> {
        return try {
            val page = params.key ?: 0
            val result = ApiService.getApi().getSearchDataByKey(page, query)
            LoadResult.Page(
                data = result.data.datas,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (result.data.curPage == result.data.pageCount) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}