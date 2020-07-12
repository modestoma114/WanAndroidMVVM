package me.robbin.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.paging.LivePagedListBuilder
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

    fun getArticle() = articleRepository.getArticle().asLiveData()

}