package me.robbin.wanandroid.ui.fragment.me.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.robbin.wanandroid.ui.fragment.common.viewmodel.BaseArticlesViewModel
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.model.ArticleBean
import me.robbin.wanandroid.model.ChapterBean
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType

/**
 * 项目界面 ViewModel
 * Create by Robbin at 2020/7/27
 */
class ProjectViewModel : BaseArticlesViewModel() {

    val projectList: MutableLiveData<MutableList<ChapterBean>> = MutableLiveData()

    val cid: MutableLiveData<Int> = MutableLiveData()

    /**
     * 获取项目分类列表
     * Create by Robbin at 2020/7/27
     */
    fun getProjectList() =
        launchGo(
            block = { projectList.value = ApiService.getApi().getProjectList().data }
        )

    /**
     * 根据 cid 获取文章列表
     * Create by Robbin at 2020/7/27
     */
    override fun getArticles(type: ArticleType, cid: Int): Flow<PagingData<ArticleBean>> =
        articleRepository.getArticles(ArticleType.PROJECT, cid)

}