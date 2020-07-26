package me.robbin.wanandroid.data.datasource

import androidx.paging.PagingSource
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.IntegralBean
import retrofit2.HttpException
import java.io.IOException

/**
 *
 * Create by Robbin at 2020/7/26
 */
class IntegralRankDataSource : PagingSource<Int, IntegralBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, IntegralBean> {
        return try {
            val page: Int = params.key ?: 1
            val response = ApiService.getApi().getIntegralRank(page)
            LoadResult.Page(
                data = response.data.datas,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.data.curPage == response.data.pageCount) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}