package me.robbin.wanandroid.ui.fragment.knowledge.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.wanandroid.app.base.BaseVM
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.model.ChapterBean

/**
 *
 * Create by Robbin at 2020/7/30
 */
class ChapterArticlesViewModel : BaseVM() {

    val knowledgeList: MutableLiveData<MutableList<ChapterBean>> = MutableLiveData()

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