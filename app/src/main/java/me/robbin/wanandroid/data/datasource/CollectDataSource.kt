package me.robbin.wanandroid.data.datasource

import androidx.paging.PagingSource
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.CollectBean
import retrofit2.HttpException
import java.io.IOException

/**
 * 收藏列表数据源
 * Create by Robbin at 2020/7/26
 */
class CollectDataSource : PagingSource<Int, CollectBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectBean> {
        return try {
            val page: Int = params.key ?: 0
            val response = ApiService.getApi().getCollectArticles(page)
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