package me.robbin.wanandroid.data.datasource

import androidx.paging.PagingSource
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.TodoBean
import retrofit2.HttpException
import java.io.IOException

/**
 * TodoL 列表数据源
 * Create by Robbin at 2020/7/27
 */
class TodoDataSource : PagingSource<Int, TodoBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TodoBean> {
        return try {
            val page: Int = params.key ?: 1
            val response = ApiService.getApi().getTodoList(page)

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