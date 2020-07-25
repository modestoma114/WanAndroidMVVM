package me.robbin.wanandroid.ui.fragment.me

import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentCollectBinding
import me.robbin.wanandroid.viewmodel.CollectViewModel

/**
 *
 * Create by Robbin at 2020/7/21
 */
class CollectFragment : BaseDBFragment<CollectViewModel, FragmentCollectBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_collect, BR.viewModel, mViewModel)
    }

}