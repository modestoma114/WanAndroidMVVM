package me.robbin.wanandroid.ui.fragment.common.view

import android.os.Bundle
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.LayoutArticleListBinding
import me.robbin.wanandroid.ui.fragment.common.viewmodel.ArticleListViewModel

/**
 *
 * Create by Robbin at 2020/7/21
 */
class ArticleListsFragment :
    BaseArticlesFragment<ArticleListViewModel, LayoutArticleListBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.layout_article_list, BR.viewModel, mViewModel)
    }

    companion object {

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

enum class ArticleType {
    HOME, QUESTION, SHARE, TREE, PROJECT, LAST_PROJECT, PUBLIC, MY_SHARE
}