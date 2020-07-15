package me.robbin.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.ChapterBean

/**
 *
 * Create by Robbin at 2020/7/15
 */
class KnowledgeListViewModel: BaseViewModel() {

    val knowledgeList: MutableLiveData<List<ChapterBean>> = MutableLiveData()

    fun getKnowledgeList() {
        launchGo(
            { knowledgeList.value = ApiService.getApi().getKnowledgeList().data }
        )
    }

}