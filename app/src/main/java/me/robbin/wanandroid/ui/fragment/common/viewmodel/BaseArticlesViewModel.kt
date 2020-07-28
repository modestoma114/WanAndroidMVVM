package me.robbin.wanandroid.ui.fragment.common.viewmodel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.data.repository.ArticleRepository
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType

/**
 *
 * Create by Robbin at 2020/7/28
 */
abstract class BaseArticlesViewModel : BaseViewModel() {

    protected val articleRepository by lazy { ArticleRepository.instance }

    abstract fun getArticles(type: ArticleType = ArticleType.HOME, cid: Int = -1): Flow<PagingData<ArticleBean>>

}