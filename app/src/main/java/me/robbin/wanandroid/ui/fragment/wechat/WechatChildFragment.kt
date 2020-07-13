package me.robbin.wanandroid.ui.fragment.wechat

import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentWechatChildBinding
import me.robbin.wanandroid.viewmodel.WechatChildViewModel

/**
 *
 * Create by Robbin at 2020/7/13
 */
class WechatChildFragment: BaseDBFragment<WechatChildViewModel, FragmentWechatChildBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_wechat_child

}