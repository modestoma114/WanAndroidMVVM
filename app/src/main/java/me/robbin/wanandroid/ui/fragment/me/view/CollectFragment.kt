package me.robbin.wanandroid.ui.fragment.me.view

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_collect.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.ext.init
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.databinding.FragmentCollectBinding
import me.robbin.wanandroid.ui.fragment.me.viewmodel.MyCollectViewModel

/**
 * 收藏列表 Fragment
 * Create by Robbin at 2020/7/21
 */
class CollectFragment : BaseFragment<MyCollectViewModel, FragmentCollectBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_collect, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    private val wanCollect by lazy { CollectArticlesFragment.newInstance(CollectType.WAN) }
    private val userCollect by lazy { CollectArticlesFragment.newInstance(CollectType.USER) }

    override fun initView(savedInstanceState: Bundle?) {
        vpCollect.init(childFragmentManager, lifecycle, arrayListOf(wanCollect, userCollect))
        TabLayoutMediator(tabCollect, vpCollect) { tab, position ->
            when (position) {
                0 -> tab.text = "站内"
                1 -> tab.text = "站外"
            }
        }.attach()
    }

    inner class ClickProxy {
        fun back() = nav().navigateUp()
    }

}