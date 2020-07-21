package me.robbin.wanandroid.ui.fragment.todo

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_todo.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.ext.viewmodel.getAppVM
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.mvvmscaffold.utils.setStatusBarLightMode
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.viewmodel.AppViewModel

/**
 *
 * Create by Robbin at 2020/7/14
 */
class TodoFragment : BaseVMFragment<BaseViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_todo

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbarTodo.addTopPadding(StatusBarUtils.getStatusBarHeight())
    }

    override fun onResume() {
        super.onResume()
        setStatusBarLightMode(true)
    }

}