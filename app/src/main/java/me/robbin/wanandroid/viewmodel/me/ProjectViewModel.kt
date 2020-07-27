package me.robbin.wanandroid.viewmodel.me

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.ChapterBean
import me.robbin.wanandroid.data.repository.ArticleRepository
import me.robbin.wanandroid.ui.fragment.common.ArticleType

/**
 *
 * Create by Robbin at 2020/7/27
 */
class ProjectViewModel : BaseViewModel() {

    private val articleRepository by lazy { ArticleRepository.instance }

    val projectList: MutableLiveData<MutableList<ChapterBean>> = MutableLiveData()

    val cid: MutableLiveData<Int> = MutableLiveData()

    fun getProjectList() =
        launchGo(
            block = { projectList.value = ApiService.getApi().getProjectList().data }
        )

    fun getProjectArticles(cid: Int) =
        articleRepository.getArticles(ArticleType.PROJECT, cid)

}