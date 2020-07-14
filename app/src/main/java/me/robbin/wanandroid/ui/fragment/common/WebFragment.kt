package me.robbin.wanandroid.ui.fragment.common

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_web.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.viewmodel.WebViewModel

/**
 *
 * Create by Robbin at 2020/7/11
 */
class WebFragment: BaseVMFragment<WebViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_web

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbarWeb.addTopPadding(108)
        tvWebTitle.text = "山西大学山西大学山西大学山西大学山西大学山西大学山西大学"
    }

}