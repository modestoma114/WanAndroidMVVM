package me.robbin.wanandroid.ui.fragment.me

import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentProfileBinding

/**
 *
 * Create by Robbin at 2020/7/21
 */
class ProfileFragment: BaseDBFragment<BaseViewModel, FragmentProfileBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_profile

}