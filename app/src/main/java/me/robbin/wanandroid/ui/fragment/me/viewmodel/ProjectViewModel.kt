package me.robbin.wanandroid.ui.fragment.me.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.robbin.wanandroid.ui.fragment.common.viewmodel.BaseArticlesViewModel
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.data.bean.ChapterBean
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType

/**
 *
 * Create by Robbin at 2020/7/27
 */
class ProjectViewModel : BaseArticlesViewModel() {

    val projectList: MutableLiveData<MutableList<ChapterBean>> = MutableLiveData()

    val cid: MutableLiveData<Int> = MutableLiveData()

    fun getProjectList() =
        launchGo(
            block = { projectList.value = ApiService.getApi().getProjectList().data }
        )

    override fun getArticles(type: ArticleType, cid: Int): Flow<PagingData<ArticleBean>> =
        articleRepository.getArticles(ArticleType.PROJECT, cid)

}