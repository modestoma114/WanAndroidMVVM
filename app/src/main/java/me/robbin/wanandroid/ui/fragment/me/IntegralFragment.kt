package me.robbin.wanandroid.ui.fragment.me

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
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.adapter.IntegralAdapter
import me.robbin.wanandroid.viewmodel.me.IntegralViewModel

/**
 *
 * Create by Robbin at 2020/7/21
 */
class IntegralFragment : BaseFragment<IntegralViewModel, FragmentIntegralBinding>() {

    private val integralAdapter by lazy { IntegralAdapter(requireContext()) }

    private var integralJob: Job? = null

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_integral, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, IntegralClick())
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
    }

    override fun initData() {
        mViewModel.getIntegral()
        refreshIntegral.setOnRefreshListener {
            mViewModel.getIntegral()
            getIntegralDetail()
        }
        rlIntegral.adapter = integralAdapter
        integralJob?.cancel()
        getIntegralDetail()
    }

    private fun getIntegralDetail() {
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
    }

    override fun onDestroy() {
        super.onDestroy()
        integralJob?.cancel()
    }

    inner class IntegralClick {
        fun back() = nav().navigateUp()
        fun goRank() = nav().navigate(R.id.action_integral_to_integralRank)
    }

}