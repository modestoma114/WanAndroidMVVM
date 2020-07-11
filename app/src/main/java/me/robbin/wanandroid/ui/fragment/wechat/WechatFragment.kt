package me.robbin.wanandroid.ui.fragment.wechat

import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentWechatBinding

/**
 *
 * Create by Robbin at 2020/7/10
 */
class WechatFragment: BaseDBFragment<BaseViewModel, FragmentWechatBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_wechat

}