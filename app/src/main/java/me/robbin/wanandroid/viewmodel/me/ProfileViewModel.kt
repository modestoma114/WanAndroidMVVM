package me.robbin.wanandroid.viewmodel.me

import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.repository.ArticleRepository

/**
 *
 * Create by Robbin at 2020/7/25
 */
class ProfileViewModel : BaseViewModel() {

    private val articleRepository by lazy { ArticleRepository.instance }

    fun getArticle() = articleRepository.getHomeArticles()

}