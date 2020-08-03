package me.robbin.wanandroid.data.datasource

import androidx.paging.PagingSource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.model.ArticleBean
import me.robbin.wanandroid.ui.fragment.home.viewmodel.HomeViewModel
import retrofit2.HttpException
import java.io.IOException

/**
 * 首页列表数据源
 * Create by Robbin at 2020/7/14
 */
class HomeDataSource(private val homeViewModel: HomeViewModel) : PagingSource<Int, ArticleBean>() {

    private val api by lazy { ApiService.getApi() }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleBean> {

        return try {
            val page = params.key ?: 0
            var data: MutableList<ArticleBean> = mutableListOf()
            var curPage = 0
            var pageCount = 1
            coroutineScope {
                homeViewModel.run {
                    launchOnlyResult(
                        block = {
                            if (page == 0) {
                            val result = api.getHomeArticles(page)
                            val banner = api.getBanners()
                            val top = api.getTopArticles()
                            result.data.datas.addAll(0, top.data)
                            result.data.datas[0].bannerList = banner.data
                            result
                        } else {
                            api.getHomeArticles(page)
                        }
                        },
                        success = {
                            data = it.datas
                            curPage = it.curPage
                            pageCount = it.pageCount
                        }
                    )
                }
                delay(2000)
                data.size.toToast()
                LoadResult.Page(
                    data = data,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (curPage == pageCount) null else page + 1
                )
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }

}