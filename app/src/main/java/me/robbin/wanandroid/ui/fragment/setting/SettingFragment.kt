package me.robbin.wanandroid.ui.fragment.setting

import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.databinding.FragmentSettingBinding
import me.robbin.wanandroid.ext.nav

/**
 * 设置 Fragment
 * Create by Robbin at 2020/7/20
 */
class SettingFragment : BaseFragment<BaseViewModel, FragmentSettingBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_setting, BR.state, appViewModel)
            .addBindingParams(BR.click, SettingClick())
    }

    inner class SettingClick {
        fun back() {
            nav().navigateUp()
        }

        fun logOut() {
            back()
            appViewModel.clearUser()
        }
    }

}