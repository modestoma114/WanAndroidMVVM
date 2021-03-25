package me.robbin.wanandroid.data.datasource

import androidx.paging.PagingSource
import me.robbin.wanandroid.app.network.EmptyException
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.model.Article
import retrofit2.HttpException
import java.io.IOException

/**
 *
 * Create by Robbin at 2020/7/30
 */
class MyShareDataSource : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page: Int = params.key ?: 1
            val response = ApiService.getApi().getMyShare(page)
            if (response.data.shareArticles.total == 0)
                throw EmptyException("100", "什么都没有找到")
            LoadResult.Page(
                data = response.data.shareArticles.datas,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.data.shareArticles.curPage == response.data.shareArticles.pageCount) null else page + 1
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