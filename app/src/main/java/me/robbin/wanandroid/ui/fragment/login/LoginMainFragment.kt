package me.robbin.wanandroid.ui.fragment.login

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_login_main.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.ext.viewmodel.getAppVM
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.ext.backMain
import me.robbin.wanandroid.ext.init
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.viewmodel.AppViewModel

/**
 *
 * Create by Robbin at 2020/7/11
 */
class LoginMainFragment : BaseVMFragment<BaseViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_login_main

    private val loginFragment by lazy { LoginFragment() }
    private val registerFragment by lazy { RegisterFragment() }

    private val appViewModel by lazy { getAppVM<AppViewModel>() }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        btnLoginClose.addTopPadding(StatusBarUtils.getStatusBarHeight())
        btnLoginClose.setOnClickListener {
            nav().navigateUp()
        }
        vpLogin.init(childFragmentManager, lifecycle, arrayListOf(loginFragment, registerFragment))
        backMain(appViewModel)
    }

    fun goLogin() {
        vpLogin.currentItem = 0
    }

    fun goRegister() {
        vpLogin.currentItem = 1
    }

}