package me.robbin.wanandroid.ui.fragment.me.view

import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ui.fragment.common.view.BaseArticlesFragment
import me.robbin.wanandroid.databinding.FragmentProfileBinding
import me.robbin.wanandroid.ui.fragment.me.viewmodel.ProfileViewModel

/**
 * 用户信息 Fragment
 * Create by Robbin at 2020/7/21
 */
class ProfileFragment : BaseArticlesFragment<ProfileViewModel, FragmentProfileBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_profile, BR.viewModel, mViewModel)
    }

}