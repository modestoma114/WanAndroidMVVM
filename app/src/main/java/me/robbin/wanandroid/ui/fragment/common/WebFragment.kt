package me.robbin.wanandroid.ui.fragment.common

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_web.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.ext.viewmodel.getAppVM
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.viewmodel.AppViewModel
import me.robbin.wanandroid.viewmodel.WebViewModel

/**
 *
 * Create by Robbin at 2020/7/11
 */
class WebFragment : BaseVMFragment<WebViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_web

    private var article: ArticleBean? = null

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbarWeb.addTopPadding(StatusBarUtils.getStatusBarHeight())
        arguments?.let {
            article = it.getParcelable("data")
        }
        tvWebTitle.text = article?.title
        webDetail.loadUrl(article?.link!!)
        webDetail.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }
    }

}