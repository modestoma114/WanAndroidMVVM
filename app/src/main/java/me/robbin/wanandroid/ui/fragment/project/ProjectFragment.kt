package me.robbin.wanandroid.ui.fragment.project

import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentProjectBinding

/**
 *
 * Create by Robbin at 2020/7/10
 */
class ProjectFragment: BaseDBFragment<BaseViewModel, FragmentProjectBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_project

}