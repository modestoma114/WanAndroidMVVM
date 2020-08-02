package me.robbin.wanandroid.ui.fragment.home.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.robbin.wanandroid.ui.fragment.common.viewmodel.BaseArticlesViewModel
import me.robbin.wanandroid.app.watcher.SimpleWatcher
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.model.ArticleBean
import me.robbin.wanandroid.model.HotKeyBean
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType

/**
 * 搜索界面 ViewModel
 * Create by Robbin at 2020/7/19
 */
class SearchViewModel : BaseArticlesViewModel() {

    /**
     * 获取搜索文章列表
     * Create by Robbin at 2020/7/28
     */
    override fun getArticles(type: ArticleType, cid: Int): Flow<PagingData<ArticleBean>> =
        articleRepository.getSearchArticles(searchStr.value.toString())

    // 搜索关键词
    val searchStr: MutableLiveData<String> = MutableLiveData("")

    // 搜索热词列表
    val hotKeys: MutableLiveData<List<HotKeyBean>> = MutableLiveData()

    // 自定义 EditText Watcher
    val searchWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            searchStr.value = s.toString()
        }
    }

    /**
     * 获取搜索热词
     * Create by Robbin at 2020/7/28
     */
    fun getHotKey() {
        launchGo(
            { hotKeys.value = ApiService.getApi().getHotKey().data }
        )
    }

}