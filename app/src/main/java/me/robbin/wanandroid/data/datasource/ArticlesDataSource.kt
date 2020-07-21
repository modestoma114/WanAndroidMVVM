package me.robbin.wanandroid.data.datasource

import androidx.paging.PagingSource
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.ui.fragment.common.ArticleType
import retrofit2.HttpException
import java.io.IOException

/**
 *
 * Create by Robbin at 2020/7/14
 */
class ArticlesDataSource(private val type: ArticleType, private val cid: Int = -1) :
    PagingSource<Int, ArticleBean>() {

    private val api by lazy { ApiService.getApi() }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleBean> {
        return try {
            val page = params.key ?: 0
            val response =
                when (type) {
                    ArticleType.QUESTION -> api.getQuestionArticles(page + 1)
                    ArticleType.SHARE -> api.getShareArticles(page)
                    ArticleType.TREE -> api.getKnowledgeArticles(page, cid)
                    ArticleType.LAST_PROJECT -> api.getLastProjectArticles(page)
                    ArticleType.PROJECT -> api.getProjectArticles(page, cid)
                    ArticleType.PUBLIC -> api.getPublicArticles(cid, page)
                    else -> ApiService.getApi().getShareArticles(page)
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