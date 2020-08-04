package me.robbin.wanandroid.ui.fragment.home.viewmodel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.data.repository.ArticleRepository
import me.robbin.wanandroid.model.ArticleBean

/**
 * 首页 ViewModel
 * Create by Robbin at 2020/7/10
 */
class HomeViewModel : BaseViewModel() {

    private val homeRepository by lazy { ArticleRepository.instance }

    /**
     * 获取首页文章列表
     * Create by Robbin at 2020/7/28
     */
    fun getArticles(): Flow<PagingData<ArticleBean>> =
        homeRepository.getHomeArticles()

    private val api by lazy { ApiService.getApi() }

    fun collect(aid: Int, success: () -> Unit) {
        launchOnlyResult(
            block = { api.collect(aid) },
            success = { success() }
        )
    }

    fun unCollect(aid: Int, success: () -> Unit) {
        launchOnlyResult(
            block = { api.deCollect(aid) },
            success = { success() }
        )
    }

}