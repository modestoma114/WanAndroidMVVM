package me.robbin.wanandroid.ui.fragment.me.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_integral.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.databinding.FragmentIntegralBinding
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.ui.fragment.me.adapter.IntegralAdapter
import me.robbin.wanandroid.ui.fragment.me.viewmodel.IntegralViewModel

/**
 * 积分 Fragment
 * Create by Robbin at 2020/7/21
 */
class IntegralFragment : BaseFragment<IntegralViewModel, FragmentIntegralBinding>() {

    private val integralAdapter by lazy { IntegralAdapter(requireContext()) }

    private var integralJob: Job? = null

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_integral, BR.viewModel, mViewModel)
    }

    override fun initView(savedInstanceState: Bundle?) {
        toolbarIntegral.setOnMenuItemClickListener {
            if (it.itemId == R.id.integralRank) {
                nav().navigate(R.id.action_integral_to_integralRank)
                true
            } else {
                false
            }
        }
        refreshIntegral.setOnRefreshListener {
            mViewModel.getIntegral()
            getIntegralDetail()
        }
        initAdapter()
        btnRule.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("url", "https://www.wanandroid.com/blog/show/2653")
            bundle.putString("title", "本站积分规则")
            nav().navigate(R.id.action_global_to_webFragment, bundle)
        }
    }

    override fun initData() {
        mViewModel.getIntegral()
        getIntegralDetail()
    }

    private fun initAdapter() {
        rlIntegral.adapter = integralAdapter
    }

    private fun getIntegralDetail() {
        integralJob?.cancel()
        integralJob = lifecycleScope.launchWhenResumed {
            mViewModel.getIntegralDetail().collect {
                integralAdapter.submitData(it)
            }
        }
    }

    override fun createObserver() {
        mViewModel.autoRefresh.observe(viewLifecycleOwner, Observer {
            refreshIntegral.isRefreshing = it
        })
        mViewModel.back.observe(viewLifecycleOwner, Observer {
            if (it) nav().navigateUp()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        integralJob?.cancel()
    }

}