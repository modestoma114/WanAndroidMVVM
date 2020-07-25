package me.robbin.wanandroid.data.datasource

import androidx.paging.PagingSource
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.IntegralDetailBean
import retrofit2.HttpException
import java.io.IOException

/**
 *
 * Create by Robbin at 2020/7/25
 */
class IntegralDataSource : PagingSource<Int, IntegralDetailBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, IntegralDetailBean> {
        return try {
            val page: Int = params.key ?: 0
            val response = ApiService.getApi().getIntegralDetail(page)
            LoadResult.Page(
                data = response.data.datas,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.data.curPage == response.data.pageCount) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}