package me.robbin.wanandroid.ui.fragment.home.viewmodel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.robbin.wanandroid.ui.fragment.common.viewmodel.BaseArticlesViewModel
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType

/**
 *
 * Create by Robbin at 2020/7/10
 */
class HomeViewModel : BaseArticlesViewModel() {

    override fun getArticles(type: ArticleType, cid: Int): Flow<PagingData<ArticleBean>> =
        articleRepository.getHomeArticles()

}