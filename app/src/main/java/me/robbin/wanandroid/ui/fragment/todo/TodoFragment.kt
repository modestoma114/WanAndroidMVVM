package me.robbin.wanandroid.ui.fragment.todo

import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.R

/**
 *
 * Create by Robbin at 2020/7/14
 */
class TodoFragment: BaseVMFragment<BaseViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_todo

}