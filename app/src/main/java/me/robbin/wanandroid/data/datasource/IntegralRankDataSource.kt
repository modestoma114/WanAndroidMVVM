package me.robbin.wanandroid.data.datasource

import androidx.paging.PagingSource
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.IntegralBean
import retrofit2.HttpException
import java.io.IOException

/**
 * 积分排名列表数据源
 * Create by Robbin at 2020/7/26
 */
class IntegralRankDataSource : PagingSource<Int, IntegralBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, IntegralBean> {
        return try {
            val page: Int = params.key ?: 1
            val response = ApiService.getApi().getIntegralRank(page)

            // 如果获取结果为空，直接抛出 Empty 异常
            if (response.data.total == 0) {
                throw EmptyException("100", "返回结果为空")
            }
            LoadResult.Page(
                data = response.data.datas,
                prevKey = if (page == 1) null else page - 1,
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