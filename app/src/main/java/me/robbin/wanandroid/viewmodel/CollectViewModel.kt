package me.robbin.wanandroid.viewmodel

import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.repository.ArticleRepository

/**
 *
 * Create by Robbin at 2020/7/25
 */
class CollectViewModel : BaseViewModel() {

    private val articleRepository by lazy { ArticleRepository.instance }

    fun getMyCollect() =
        launchGo(
            block = { articleRepository.getHomeArticles() }
        )

}