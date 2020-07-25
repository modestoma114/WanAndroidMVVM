package me.robbin.wanandroid.ui.fragment.me

import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentProfileBinding
import me.robbin.wanandroid.viewmodel.ProfileViewModel

/**
 *
 * Create by Robbin at 2020/7/21
 */
class ProfileFragment : BaseDBFragment<ProfileViewModel, FragmentProfileBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_profile, BR.viewModel, mViewModel)
    }

}