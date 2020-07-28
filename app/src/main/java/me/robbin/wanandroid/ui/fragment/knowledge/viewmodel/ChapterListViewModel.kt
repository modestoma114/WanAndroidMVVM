package me.robbin.wanandroid.ui.fragment.knowledge.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.ChapterBean
import me.robbin.wanandroid.data.bean.NavigationBean

/**
 *
 * Create by Robbin at 2020/7/15
 */
class ChapterListViewModel : BaseViewModel() {

    val knowledgeList: MutableLiveData<MutableList<ChapterBean>> = MutableLiveData()
    val navigationList: MutableLiveData<MutableList<NavigationBean>> = MutableLiveData()
    val publicList: MutableLiveData<MutableList<ChapterBean>> = MutableLiveData()

    fun getKnowledgeList() {
        launchGo(
            { knowledgeList.value = ApiService.getApi().getKnowledgeList().data }
        )
    }

    fun getNavigationList() {
        launchGo(
            { navigationList.value = ApiService.getApi().getNaviList().data }
        )
    }

    fun getPublicList() {
        launchGo(
            { publicList.value = ApiService.getApi().getPublicList().data }
        )
    }

}