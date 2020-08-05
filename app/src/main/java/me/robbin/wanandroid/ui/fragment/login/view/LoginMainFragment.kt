package me.robbin.wanandroid.ui.fragment.login.view

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_login.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.ext.backMain
import me.robbin.wanandroid.app.ext.init
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.databinding.FragmentLoginBinding

/**
 * 登录/注册 Fragment
 * Create by Robbin at 2020/7/11
 */
class LoginMainFragment : BaseFragment<BaseViewModel, FragmentLoginBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_login, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    private val loginFragment by lazy { LoginFragment() }
    private val registerFragment by lazy { RegisterFragment() }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        vpLogin.init(childFragmentManager, lifecycle, arrayListOf(loginFragment, registerFragment))
        backMain()
    }

    fun goLogin() {
        vpLogin.currentItem = 0
    }

    fun goRegister() {
        vpLogin.currentItem = 1
    }

    inner class ClickProxy {
        fun back() = nav().navigateUp()
    }

}