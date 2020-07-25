package me.robbin.wanandroid.ui.fragment.me

import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_integral.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentIntegralBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.adapter.IntegralAdapter
import me.robbin.wanandroid.viewmodel.IntegralViewModel

/**
 *
 * Create by Robbin at 2020/7/21
 */
class IntegralFragment : BaseDBFragment<IntegralViewModel, FragmentIntegralBinding>() {

    private val integralAdapter by lazy { IntegralAdapter(requireContext()) }

    private var integralJob: Job? = null

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_integral, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, IntegralClick())
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
    }

}