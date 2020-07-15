package me.robbin.wanandroid.data.datasource

import androidx.paging.PagingSource
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.ArticleBean
import retrofit2.HttpException
import java.io.IOException

/**
 *
 * Create by Robbin at 2020/7/14
 */
class ChapterArticleDataSource(private val type: Int, private val cid: Int = -1) :
    PagingSource<Int, ArticleBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleBean> {
        return try {
            val page = params.key ?: 0
            val response =
                when (type) {
                    1 -> ApiService.getApi().getQuestionArticleList(page + 1)
                    2 -> ApiService.getApi().getUserArticleList(page)
                    3 -> ApiService.getApi().getKnowledgeArticleList(page, cid)
                    else -> ApiService.getApi().getUserArticleList(page)
                }
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