package me.robbin.wanandroid.ui.home

import me.robbin.architecture.model.BaseViewModel
import me.robbin.wanandroid.data.repository.ArticleRepository
import me.robbin.wanandroid.ui.common.ArticlesAdapter

class HomeStateViewModel: BaseViewModel() {

    val adapter by lazy { ArticlesAdapter() }

    fun homeArticles() = ArticleRepository.homeArticles()

}