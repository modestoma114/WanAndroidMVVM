package me.robbin.wanandroid.ui.fragment.common

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_list_article.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ui.adapter.ArticleAdapter
import me.robbin.wanandroid.viewmodel.ArticleListViewModel

/**
 *
 * Create by Robbin at 2020/7/14
 */
class ArticleListFragment : BaseVMFragment<ArticleListViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_list_article

    private var type: Int = 1
    private var cid: Int = -1

    private val adapter by lazy { ArticleAdapter() }

    private var articleJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        arguments?.let {
            type = it.getInt("type")
            cid = it.getInt("cid")
        }
        rlListArticle.adapter = adapter
    }

    override fun initData() {
        articleJob?.cancel()
        articleJob = lifecycleScope.launchWhenCreated {
            mViewModel.getArticleList(type, cid).collect {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        articleJob?.cancel()
    }

    companion object {
        fun newInstance(type: Int, cid: Int = -1): ArticleListFragment {
            val args = Bundle()
            args.putInt("type", type)
            if (cid != -1) args.putInt("cid", cid)
            val fragment =
                ArticleListFragment()
            fragment.arguments = args
            return fragment
        }
    }

}