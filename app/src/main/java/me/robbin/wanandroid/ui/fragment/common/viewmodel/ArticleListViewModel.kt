package me.robbin.wanandroid.ui.fragment.common.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.data.bean.CollectBean
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType

/**
 *
 * Create by Robbin at 2020/7/14
 */
class ArticleListViewModel : BaseArticlesViewModel() {

    override fun getArticles(type: ArticleType, cid: Int): Flow<PagingData<ArticleBean>> =
        when (type) {
            ArticleType.HOME -> {
                articleRepository.getHomeArticles()
            }
            else -> {
                articleRepository.getArticles(type, cid)
            }
        }

    fun getCollect(): Flow<PagingData<CollectBean>> {
        return articleRepository.getCollectArticles().cachedIn(viewModelScope)
    }

}