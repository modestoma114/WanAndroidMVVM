package me.robbin.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.bean.ChapterBean
import me.robbin.wanandroid.data.repository.WechatRepository

/**
 *
 * Create by Robbin at 2020/7/13
 */
class WechatViewModel : BaseViewModel() {

    private val wechatRepository by lazy { WechatRepository() }

    val wechatList: MutableLiveData<List<ChapterBean>> = MutableLiveData()

    fun getWechatList() {
        launchGo(
            { wechatList.value = wechatRepository.getWechatList().data }
        )
    }

}