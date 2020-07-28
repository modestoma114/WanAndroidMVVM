package me.robbin.wanandroid.ui.fragment.home.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.robbin.wanandroid.ui.fragment.common.viewmodel.BaseArticlesViewModel
import me.robbin.wanandroid.app.watcher.SimpleWatcher
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.data.bean.HotKeyBean
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType

/**
 *
 * Create by Robbin at 2020/7/19
 */
class SearchViewModel : BaseArticlesViewModel() {

    override fun getArticles(type: ArticleType, cid: Int): Flow<PagingData<ArticleBean>> =
        articleRepository.getSearchArticles(searchStr.value.toString())

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

}