package me.robbin.wanandroid.ui.fragment.login

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_login.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.databinding.FragmentLoginBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.viewmodel.login.LoginViewModel

/**
 *
 * Create by Robbin at 2020/7/17
 */
class LoginFragment : BaseFragment<BaseViewModel, FragmentLoginBinding>() {

    private val userViewModel by activityViewModels<LoginViewModel>()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_login, BR.viewModel, userViewModel)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        btnGoRegister.setOnClickListener {
            (parentFragment as LoginMainFragment).goRegister()
        }
        btnLogin.setOnClickListener {
            userViewModel.login {
                appViewModel.setIsLogin(true)
                nav().navigateUp()
            }
        }
    }

}