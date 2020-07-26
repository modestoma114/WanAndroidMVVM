package me.robbin.wanandroid.viewmodel.home

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.app.watcher.SimpleWatcher
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.HotKeyBean
import me.robbin.wanandroid.data.repository.ArticleRepository

/**
 *
 * Create by Robbin at 2020/7/19
 */
class SearchViewModel : BaseViewModel() {

    private val articleRepository by lazy { ArticleRepository.instance }

    val searchStr: MutableLiveData<String> = MutableLiveData("")

    val hotKeys: MutableLiveData<List<HotKeyBean>> = MutableLiveData()

    val searchWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            searchStr.value = s.toString()
        }
    }

    fun getHotKey() {
        launchGo(
            { hotKeys.value = ApiService.getApi().getHotKey().data }
        )
    }

    fun searchArticle() = articleRepository.getSearchArticles(searchStr.value.toString())

}