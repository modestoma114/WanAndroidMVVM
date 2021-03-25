package me.robbin.wanandroid.ui.fragment.common.viewmodel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.robbin.wanandroid.model.ArticleBean
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType

/**
 * 通用文章列表 ViewModel
 * Create by Robbin at 2020/7/14
 */
class ArticleListViewModel : BaseArticlesViewModel() {

    override fun getArticles(type: ArticleType, cid: Int): Flow<PagingData<ArticleBean>> =
        articleRepository.getArticles(type, cid)

}