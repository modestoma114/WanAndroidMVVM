package me.robbin.wanandroid.data.datasource

import androidx.paging.PagingSource
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.model.ArticleBean
import retrofit2.HttpException
import java.io.IOException

/**
 * 搜索结果数据源
 * Create by Robbin at 2020/7/19
 */
class SearchArticleDateSource(private val query: String) : PagingSource<Int, ArticleBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleBean> {
        return try {
            val page = params.key ?: 0
            val response = ApiService.getApi().getSearchDataByKey(page, query)

            // 如果获取结果为空，直接抛出 Empty 异常
            if (response.data.total == 0) {
                throw EmptyException("100", "返回结果为空")
            }
            LoadResult.Page(
                data = response.data.datas,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.data.curPage == response.data.pageCount) null else page + 1
            )
        } catch (exception: EmptyException) {
            return LoadResult.Error(exception)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}