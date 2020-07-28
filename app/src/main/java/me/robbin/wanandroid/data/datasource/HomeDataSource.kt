package me.robbin.wanandroid.data.datasource

import androidx.paging.PagingSource
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.ArticleBean
import retrofit2.HttpException
import java.io.IOException

/**
 * 首页列表数据源
 * Create by Robbin at 2020/7/14
 */
class HomeDataSource : PagingSource<Int, ArticleBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleBean> {

        return try {
            val page = params.key ?: -1
            val (data, curPage, pageCount) = ApiService.getArticle(page)
            LoadResult.Page(
                data = data,
                prevKey = if (page == -1) null else page - 1,
                nextKey = if (curPage == pageCount) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }

}