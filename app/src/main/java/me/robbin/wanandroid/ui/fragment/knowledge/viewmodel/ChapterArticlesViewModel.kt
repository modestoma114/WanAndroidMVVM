package me.robbin.wanandroid.ui.fragment.knowledge.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.model.Chapter

/**
 *
 * Create by Robbin at 2020/7/30
 */
class ChapterArticlesViewModel : BaseViewModel() {

    val knowledgeList: MutableLiveData<MutableList<Chapter>> = MutableLiveData()

    val title: MutableLiveData<String> = MutableLiveData()

    /**
     * 获取体系列表
     * Create by Robbin at 2020/7/15
     */
    fun getKnowledgeList() {
        launchGo(
            { knowledgeList.value = ApiService.getApi().getKnowledgeList().data }
        )
    }

}