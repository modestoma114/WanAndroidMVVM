package me.robbin.wanandroid.ui.fragment.me.view

import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_integral_rank.*
import kotlinx.coroutines.flow.collect
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.databinding.FragmentIntegralRankBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.me.adapter.IntegralRankAdapter
import me.robbin.wanandroid.ui.fragment.me.viewmodel.IntegralRankViewModel

/**
 *
 * Create by Robbin at 2020/7/26
 */
class IntegralRankFragment : BaseFragment<IntegralRankViewModel, FragmentIntegralRankBinding>() {

    private val rankAdapter by lazy {
        IntegralRankAdapter(
            requireContext()
        )
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_integral_rank, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, Click())
    }

    override fun initData() {
        rlRank.adapter = rankAdapter
        lifecycleScope.launchWhenCreated {
            mViewModel.getIntegralRank().collect {
                rankAdapter.submitData(it)
            }
        }
    }

    override fun createObserver() {
        mViewModel.autoRefresh.observe(viewLifecycleOwner, Observer {
            refreshRank.isRefreshing = it
        })
    }

    inner class Click {
        fun back() = nav().navigateUp()
    }

}