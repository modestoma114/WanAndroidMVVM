package me.robbin.wanandroid.ui.fragment.common.view

import android.os.Bundle
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.LayoutArticlesBinding
import me.robbin.wanandroid.ui.fragment.common.viewmodel.ArticleListViewModel

/**
 * 通用文章列表 Fragment
 * Create by Robbin at 2020/7/21
 */
class ArticleListsFragment :
    BaseArticlesFragment<ArticleListViewModel, LayoutArticlesBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.layout_articles, BR.viewModel, mViewModel)
    }

    companion object {

        /**
         * 根据 type 和 cid 获取不同 Fragment 实例
         * @param type 文章类型
         * @param cid 文章模块Id
         * Create by Robbin at 2020/7/21
         */
        fun newInstance(type: ArticleType, cid: Int = -1): ArticleListsFragment {
            val args = Bundle()
            args.putSerializable("type", type)
            args.putInt("cid", cid)
            val fragment =
                ArticleListsFragment()
            fragment.arguments = args
            return fragment
        }

    }

}

/**
 * 文章类型枚举类
 * Create by Robbin at 2020/7/21
 */
enum class ArticleType {
    HOME, QUESTION, SHARE, TREE, PROJECT, LAST_PROJECT, PUBLIC, MY_SHARE
}