package me.robbin.wanandroid.ui.fragment.common.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel

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

}