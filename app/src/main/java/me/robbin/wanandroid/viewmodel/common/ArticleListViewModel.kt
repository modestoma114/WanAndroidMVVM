package me.robbin.wanandroid.viewmodel.common

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import me.robbin.wanandroid.app.base.BaseVM
import me.robbin.wanandroid.data.bean.CollectBean
import me.robbin.wanandroid.data.repository.ArticleRepository
import me.robbin.wanandroid.ui.fragment.common.ArticleType

/**
 *
 * Create by Robbin at 2020/7/14
 */
class ArticleListViewModel : BaseVM() {

    private val articleRepository by lazy { ArticleRepository.instance }

    fun getArticles(type: ArticleType, cid: Int = -1) = when (type) {
        ArticleType.HOME -> {
            autoRefresh.value = false
            articleRepository.getHomeArticles()
        }
        else -> {
            autoRefresh.value = false
            articleRepository.getArticles(type, cid)
        }
    }

    fun getCollect(): Flow<PagingData<CollectBean>> {
        autoRefresh.value = false
        return articleRepository.getCollectArticles().cachedIn(viewModelScope)
    }

}