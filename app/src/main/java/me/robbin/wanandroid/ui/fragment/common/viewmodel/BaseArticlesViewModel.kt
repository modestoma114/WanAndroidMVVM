package me.robbin.wanandroid.ui.fragment.common.viewmodel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.data.repository.ArticleRepository
import me.robbin.wanandroid.model.ArticleBean
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType

/**
 * 文章列表基类 ViewModel
 * Create by Robbin at 2020/7/28
 */
abstract class BaseArticlesViewModel : BaseViewModel() {

    protected val articleRepository by lazy { ArticleRepository.instance }

    abstract fun getArticles(type: ArticleType = ArticleType.HOME, cid: Int = -1): Flow<PagingData<ArticleBean>>

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