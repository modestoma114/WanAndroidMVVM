package me.robbin.wanandroid.ui.fragment.login.view

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_register.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.databinding.FragmentRegisterBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.login.viewmodel.LoginViewModel

/**
 * 注册 Fragment
 * Create by Robbin at 2020/7/17
 */
class RegisterFragment : BaseFragment<BaseViewModel, FragmentRegisterBinding>() {

    private val userViewModel by activityViewModels<LoginViewModel>()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_register, BR.viewModel, userViewModel)
    }

    override fun initView(savedInstanceState: Bundle?) {
        btnGoLogin.setOnClickListener {
            (parentFragment as LoginMainFragment).goLogin()
        }
        btnRegister.setOnClickListener {
            userViewModel.register {
                "注册成功".toToast()
                appViewModel.setIsLogin(true)
                nav().navigateUp()
            }
        }
    }

}