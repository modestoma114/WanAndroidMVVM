package me.robbin.wanandroid.app.base

import androidx.databinding.ViewDataBinding
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.mvvmscaffold.ext.viewmodel.getAppVM
import me.robbin.wanandroid.ui.fragment.main.viewmodel.AppViewModel

/**
 *
 * Create by Robbin at 2020/7/23
 */
abstract class BaseFragment<VM : BaseViewModel, VDB : ViewDataBinding> : BaseDBFragment<VM, VDB>() {

    protected val appViewModel by lazy { getAppVM<AppViewModel>() }

}