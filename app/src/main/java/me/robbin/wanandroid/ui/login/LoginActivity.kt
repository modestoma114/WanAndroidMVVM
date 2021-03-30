package me.robbin.wanandroid.ui.login

import android.os.Bundle
import me.robbin.architecture.base.BaseActivity
import me.robbin.architecture.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.ActivityLoginBinding

class LoginActivity: BaseActivity<LoginStateViewModel, ActivityLoginBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            layout = R.layout.activity_login,
            vmVariableId = BR.state,
            stateViewModel = mViewModel
        )
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initData() {
    }

    override fun createObserver() {
    }

}