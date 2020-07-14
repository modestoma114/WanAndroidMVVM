package me.robbin.wanandroid.viewmodel

import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.repository.ArticleRepository

/**
 *
 * Create by Robbin at 2020/7/14
 */
class ChapterChildViewModel: BaseViewModel() {

    private val articleRepository by lazy { ArticleRepository.instance }

    fun getQuestion(type: Int) = articleRepository.getQuestionArticle(type)

}