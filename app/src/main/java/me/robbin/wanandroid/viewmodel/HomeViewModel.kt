package me.robbin.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.network.RetrofitClient
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.data.bean.BannerBean
import me.robbin.wanandroid.data.network.ApiService
import me.robbin.wanandroid.data.network.WanApi
import me.robbin.wanandroid.data.repository.ArticleRepository

/**
 *
 * Create by Robbin at 2020/7/10
 */
class HomeViewModel : BaseViewModel() {

    private val articleRepository by lazy { ArticleRepository() }

    val banners: MutableLiveData<List<BannerBean>> = MutableLiveData()

    val articles: MutableLiveData<List<ArticleBean>> = MutableLiveData()

    fun getArticle() = articleRepository.getArticle().asLiveData()

    fun getData() {

        launchGo(
            {
                val response = ApiService.getApi().getBanner()
                banners.value = response.data
            }
        )

        launchGo(
            {
                val response = ApiService.getApi().getArticleList(0)
                if (response.isSuccess()) {
                    response.data.size.toToast()
                }
                articles.value = response.data.datas
            }
        )

    }

}