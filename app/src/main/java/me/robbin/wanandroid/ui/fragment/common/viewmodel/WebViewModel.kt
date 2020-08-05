package me.robbin.wanandroid.ui.fragment.common.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.network.ResponseThrowable
import me.robbin.wanandroid.api.ApiService

/**
 * 网页界面 ViewModel
 * Create by Robbin at 2020/7/13
 */
class WebViewModel : BaseViewModel() {

    var url: MutableLiveData<String> = MutableLiveData("")
    var title: MutableLiveData<String> = MutableLiveData("")
    var articleId: MutableLiveData<Int> = MutableLiveData(0)
    var collected: MutableLiveData<Boolean> = MutableLiveData(false)
    var author: MutableLiveData<String> = MutableLiveData("")
    var userId: MutableLiveData<Int> = MutableLiveData(0)

    private val api by lazy { ApiService.getApi() }

    fun collect(success: () -> Unit) {
        launchOnlyResult(
            block = {
                if (articleId.value != null) {
                    api.collect(articleId.value!!)
                } else {
                    throw ResponseThrowable("-2", "没有获取到文章Id")
                }
            },
            success = { success() }
        )
    }

    fun unCollect(success: () -> Unit) {
        launchOnlyResult(
            block = {
                if (articleId.value != null) {
                    api.deCollect(articleId.value!!)
                } else {
                    throw ResponseThrowable("-2", "没有获取到文章Id")
                }
            },
            success = { success() }
        )
    }

}