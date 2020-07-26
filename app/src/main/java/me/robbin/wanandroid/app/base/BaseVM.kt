package me.robbin.wanandroid.app.base

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel

/**
 *
 * Create by Robbin at 2020/7/26
 */
open class BaseVM : BaseViewModel() {

    val autoRefresh: MutableLiveData<Boolean> = MutableLiveData(false)

}