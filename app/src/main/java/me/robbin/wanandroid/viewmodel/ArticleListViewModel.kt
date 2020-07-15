package me.robbin.wanandroid.viewmodel

import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.repository.ArticleRepository

/**
 *
 * Create by Robbin at 2020/7/14
 */
class ArticleListViewModel: BaseViewModel() {

    private val articleRepository by lazy { ArticleRepository.instance }

    fun getArticleList(type: Int, cid: Int = -1) = articleRepository.getArticleList(type, cid)

}