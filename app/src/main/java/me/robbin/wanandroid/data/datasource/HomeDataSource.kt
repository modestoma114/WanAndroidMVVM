package me.robbin.wanandroid.data.datasource

import androidx.paging.PagingSource
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.model.ApiResponse
import me.robbin.wanandroid.model.Article
import retrofit2.HttpException
import java.io.IOException

/**
 * 首页列表数据源
 * Create by Robbin at 2020/7/14
 */
class HomeDataSource : PagingSource<Int, Article>() {

    private val api by lazy { ApiService.getApi() }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        return try {
            val page = params.key ?: 0
            var response: ApiResponse<ApiPageResponse<MutableList<Article>>>? = null
            if (page == 0) {
                ApiService.getHomeArticle(page) {
                    response = it
                }
            } else {
                response = api.getHomeArticles(page)
            }
            if (response == null) {
                throw EmptyException("-1", "没有获取到数据")
            }
            LoadResult.Page(
                data = response!!.data.datas,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response!!.data.curPage == response!!.data.pageCount) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }

}