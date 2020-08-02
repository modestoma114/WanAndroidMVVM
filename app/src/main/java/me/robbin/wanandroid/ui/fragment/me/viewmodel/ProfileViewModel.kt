package me.robbin.wanandroid.ui.fragment.me.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.wanandroid.app.base.BaseVM
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.data.repository.ArticleRepository

/**
 * 用户详细界面 ViewModel
 * Create by Robbin at 2020/7/25
 */
class ProfileViewModel : BaseVM() {

    val userName: MutableLiveData<String> = MutableLiveData("")
    val userInfo: MutableLiveData<String> = MutableLiveData("加载中，请稍等。。。")
    val haveArticle: MutableLiveData<Boolean> = MutableLiveData(false)

    private var uId: Int = 0

    private val userPageRepository by lazy { ArticleRepository.instance }

    fun getUserInfo(uId: Int) {
        this.uId = uId
        launchOnlyResult(
            block = { ApiService.getApi().getUserPage(uId, 1) },
            success = {
                userName.value = it.coinInfo.username
                userInfo.value =
                    "ID：${it.coinInfo.userId} 积分：${it.coinInfo.coinCount} 排名：${it.coinInfo.rank}"
                haveArticle.value = it.shareArticles.total != 0
            }
        )
    }

    fun getUserPage() = userPageRepository.getUserPage(uId)

}