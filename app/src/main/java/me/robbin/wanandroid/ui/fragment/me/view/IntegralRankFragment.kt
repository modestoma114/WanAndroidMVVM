package me.robbin.wanandroid.ui.fragment.me.view

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import kotlinx.android.synthetic.main.fragment_integral_rank.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.databinding.FragmentIntegralRankBinding
import me.robbin.wanandroid.ui.fragment.me.adapter.IntegralRankAdapter
import me.robbin.wanandroid.ui.fragment.me.viewmodel.IntegralRankViewModel

/**
 * 积分排行榜 Fragment
 * Create by Robbin at 2020/7/26
 */
class IntegralRankFragment : BaseFragment<IntegralRankViewModel, FragmentIntegralRankBinding>() {

    private val rankAdapter by lazy { IntegralRankAdapter(requireContext()) }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_integral_rank, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    override fun initView(savedInstanceState: Bundle?) {
        initAdapter()
    }

    override fun initData() {
        lifecycleScope.launchWhenCreated {
            mViewModel.getIntegralRank().collect {
                rankAdapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        rlRank.adapter = rankAdapter
        lifecycleScope.launchWhenCreated {
            rankAdapter.loadStateFlow.collectLatest { loadState ->
                refreshRank.isRefreshing = loadState.refresh is LoadState.Loading
            }
        }
    }

    inner class ClickProxy {
        fun back() = nav().navigateUp()
    }

}