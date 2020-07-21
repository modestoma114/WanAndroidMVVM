package me.robbin.wanandroid.viewmodel

import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.repository.ArticleRepository
import me.robbin.wanandroid.ui.fragment.common.ArticleType

/**
 *
 * Create by Robbin at 2020/7/14
 */
class ArticleListViewModel: BaseViewModel() {

    private val articleRepository by lazy { ArticleRepository.instance }

    fun getArticleList(type: ArticleType, cid: Int = -1) = when (type) {
        ArticleType.HOME -> articleRepository.getHomeArticles()
        else -> articleRepository.getArticleList(type, cid)
    }

}