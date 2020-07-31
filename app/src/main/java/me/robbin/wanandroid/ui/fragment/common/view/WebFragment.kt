package me.robbin.wanandroid.ui.fragment.common.view

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_web.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.setStatusBarLightMode
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.databinding.FragmentWebBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.common.viewmodel.WebViewModel

/**
 * 网页 Fragment
 * Create by Robbin at 2020/7/11
 */
class WebFragment : BaseFragment<WebViewModel, FragmentWebBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_web, BR.viewModel, mViewModel)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        arguments?.let {
            mViewModel.url.value = it.getString("url", "")
            mViewModel.title.value = it.getString("title", "")
            mViewModel.articleId.value = it.getInt("articleId", 0)
            mViewModel.collected.value = it.getBoolean("collected", false)
            mViewModel.author.value = it.getString("author", "")
            mViewModel.userId.value = it.getInt("userId", 0)
        }
        initWebView()
    }

    override fun createObserver() {
        mViewModel.url.observe(viewLifecycleOwner, Observer {
            webDetail.loadUrl(it)
        })
        mViewModel.back.observe(viewLifecycleOwner, Observer {
            if (it) nav().navigateUp()
        })
    }

    private fun initWebView() {
        webDetail.run {
            settings.run {
                javaScriptEnabled = true
            }
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url!!)
                    return true
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setStatusBarLightMode(!appViewModel.isNightMode.value!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (!appViewModel.isNightMode.value!!)
            setStatusBarLightMode(false)
    }

}