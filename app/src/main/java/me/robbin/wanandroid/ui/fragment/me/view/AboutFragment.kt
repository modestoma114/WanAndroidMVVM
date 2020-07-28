package me.robbin.wanandroid.ui.fragment.me.view

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_about.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentAboutBinding
import me.robbin.wanandroid.ui.fragment.me.adapter.LicensesAdapter
import me.robbin.wanandroid.ui.fragment.me.viewmodel.AboutViewModel

/**
 *
 * Create by Robbin at 2020/7/21
 */
class AboutFragment : BaseDBFragment<AboutViewModel, FragmentAboutBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_about, BR.viewModel, mViewModel)
    }

    private val licensesAdapter by lazy { LicensesAdapter() }

    override fun initView(savedInstanceState: Bundle?) {
        rlLicenses.adapter = licensesAdapter
    }

    override fun initData() {
        mViewModel.getLicenses()
    }

    override fun createObserver() {
        mViewModel.licenses.observe(viewLifecycleOwner, Observer {
            licensesAdapter.setNewInstance(it)
        })
    }

}