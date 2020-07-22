package me.robbin.wanandroid.ui.fragment.setting

import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.ext.viewmodel.getAppVM
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ext.checkLogin
import me.robbin.wanandroid.viewmodel.AppViewModel

/**
 *
 * Create by Robbin at 2020/7/20
 */
class SettingFragment : BaseVMFragment<BaseViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_setting

    private val appViewModel by lazy { getAppVM<AppViewModel>() }

    override fun createObserver() {
        checkLogin() {
            "Welcome".toToast()
        }
    }
}