package me.robbin.wanandroid.ui.fragment.me.view

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_my_share.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentMyShareBinding
import me.robbin.wanandroid.ext.checkLogin
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.common.view.BaseArticlesFragment
import me.robbin.wanandroid.ui.fragment.me.viewmodel.MyShareViewModel

/**
 * 我的分享 Fragment
 * Create by Robbin at 2020/7/26
 */
class MyShareFragment : BaseArticlesFragment<MyShareViewModel, FragmentMyShareBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_my_share, BR.viewModel, mViewModel)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbarMyShare.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.addShare)
                checkLogin {
                    nav().navigate(R.id.action_my_share_to_share_Article)
                }
            return@setOnMenuItemClickListener true
        }
    }

    override fun createObserver() {
        super.createObserver()
        mViewModel.back.observe(viewLifecycleOwner, Observer {
            if (it) nav().navigateUp()
        })
    }

}