package me.robbin.wanandroid.ui.fragment.tree

import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentTreeBinding

/**
 *
 * Create by Robbin at 2020/7/10
 */
class TreeFragment: BaseDBFragment<BaseViewModel, FragmentTreeBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_tree

}