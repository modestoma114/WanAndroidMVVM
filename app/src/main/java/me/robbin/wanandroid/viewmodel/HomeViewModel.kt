package me.robbin.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.BannerBean
import me.robbin.wanandroid.data.repository.ArticleRepository

/**
 *
 * Create by Robbin at 2020/7/10
 */
class HomeViewModel : BaseViewModel() {

    private val articleRepository by lazy { ArticleRepository.instance }

    fun getArticle() = articleRepository.getHomeArticles()

    val bannerData: MutableLiveData<List<BannerBean>> = MutableLiveData()

    fun getBanner() {
        launchGo(
            { bannerData.value = ApiService.getApi().getBanner().data }
        )
    }

}