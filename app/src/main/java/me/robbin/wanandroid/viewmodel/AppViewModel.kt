package me.robbin.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.app.utils.CacheUtils
import me.robbin.wanandroid.data.bean.UserBean

/**
 *
 * Create by Robbin at 2020/7/19
 */
class AppViewModel : BaseViewModel() {

    // 判断是否登录
    val isLogin: MutableLiveData<Boolean> = MutableLiveData(false)
    val userInfo: MutableLiveData<UserBean> = MutableLiveData()

    init {
        isLogin.value = CacheUtils.isLogin()
        userInfo.value = CacheUtils.getUser()
    }

    fun setIsLogin(isLogin: Boolean) {
        this.isLogin.value = isLogin
        if (isLogin) userInfo.value = CacheUtils.getUser()
    }

    fun clearUser() {
        isLogin.value = false
        userInfo.value = null
        CacheUtils.setUser(null)
    }

}