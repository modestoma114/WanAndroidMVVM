package me.robbin.wanandroid.data.datesource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.robbin.wanandroid.api.WanApi
import me.robbin.wanandroid.model.Article
import retrofit2.HttpException

class ArticleDataSource(
    private val service: WanApi,
): PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 0
            val response = service.homeArticles(page)
            LoadResult.Page(
                data = response.data.datas,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.data.curPage == response.data.pageCount) null else page + 1
            )
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

}