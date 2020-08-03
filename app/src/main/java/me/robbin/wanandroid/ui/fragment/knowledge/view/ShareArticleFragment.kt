package me.robbin.wanandroid.ui.fragment.knowledge.view

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_share_article.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.ext.nav
import me.robbin.wanandroid.databinding.FragmentShareArticleBinding
import me.robbin.wanandroid.ui.fragment.knowledge.viewmodel.ShareArticleViewModel

/**
 *
 * Create by Robbin at 2020/7/30
 */
class ShareArticleFragment : BaseFragment<ShareArticleViewModel, FragmentShareArticleBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_share_article, BR.viewModel, mViewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    override fun initView(savedInstanceState: Bundle?) {
        btnSend.setOnClickListener {
            mViewModel.shareArticle {
                "分享成功".toToast()
                nav().navigateUp()
            }
        }
    }

    override fun initData() {
        mViewModel.author.value = appViewModel.userInfo.value?.nickname
    }

    inner class ClickProxy {
        fun back() = nav().navigateUp()
    }

}