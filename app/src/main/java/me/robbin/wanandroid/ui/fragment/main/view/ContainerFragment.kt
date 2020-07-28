package me.robbin.wanandroid.ui.fragment.main.view

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_container.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ext.init
import me.robbin.wanandroid.ui.fragment.todo.view.TodoFragment

/**
 * 一级承载界面
 * Create by Robbin at 2020/7/14
 */
class ContainerFragment : BaseVMFragment<BaseViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_container

    private val todoFragment by lazy { TodoFragment() }
    private val mainFragment by lazy { MainFragment() }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        vpContainer.init(childFragmentManager, arrayListOf(todoFragment, mainFragment))
        vpContainer.currentItem = 1
    }

}