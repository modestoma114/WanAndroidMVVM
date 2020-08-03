package me.robbin.wanandroid.ui.fragment.me.view

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_about.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.model.LicensesBean
import me.robbin.wanandroid.databinding.FragmentAboutBinding
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.ui.fragment.me.adapter.LicensesAdapter
import me.robbin.wanandroid.ui.fragment.me.viewmodel.AboutViewModel

/**
 * 关于软件 Fragment
 * Create by Robbin at 2020/7/21
 */
class AboutFragment : BaseDBFragment<AboutViewModel, FragmentAboutBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_about, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    private val licensesAdapter by lazy { LicensesAdapter() }

    override fun initView(savedInstanceState: Bundle?) {
        rlLicenses.adapter = licensesAdapter
        licensesAdapter.setOnItemClickListener { adapter, _, position ->
            val bundle = Bundle()
            val bean = adapter.getItem(position) as LicensesBean
            bundle.putString("title", bean.name)
            bundle.putString("url", bean.url)
            nav().navigate(R.id.action_global_to_webFragment, bundle)
        }
    }

    override fun initData() {
        mViewModel.getLicenses()
    }

    override fun createObserver() {
        mViewModel.licenses.observe(viewLifecycleOwner, Observer {
            licensesAdapter.setNewInstance(it)
        })
    }

    inner class ClickProxy {
        fun back() = nav().navigateUp()
    }

}