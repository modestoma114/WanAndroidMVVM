package me.robbin.wanandroid.app.base

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.app.event.EventViewModel
import me.robbin.wanandroid.ui.fragment.main.viewmodel.AppViewModel

/**
 * 包含 AppViewModel 的 Fragment 基类
 * Create by Robbin at 2020/7/23
 */
abstract class BaseFragment<VM : BaseViewModel, VDB : ViewDataBinding> : BaseDBFragment<VM, VDB>() {

    // 获取 Activity 级别的 AppViewModel，存储一些通用信息
    protected val appViewModel by activityViewModels<AppViewModel>()

    // 全局通知 ViewModel
    protected val eventViewModel by activityViewModels<EventViewModel>()

}