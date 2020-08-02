package me.robbin.wanandroid.ui.fragment.home.viewmodel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.robbin.wanandroid.model.ArticleBean
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType
import me.robbin.wanandroid.ui.fragment.common.viewmodel.BaseArticlesViewModel

/**
 * 首页 ViewModel
 * Create by Robbin at 2020/7/10
 */
class HomeViewModel : BaseArticlesViewModel() {

    /**
     * 获取首页文章列表
     * Create by Robbin at 2020/7/28
     */
    override fun getArticles(type: ArticleType, cid: Int): Flow<PagingData<ArticleBean>> =
        articleRepository.getHomeArticles()

}