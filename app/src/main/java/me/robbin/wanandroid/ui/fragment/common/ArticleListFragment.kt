package me.robbin.wanandroid.ui.fragment.common

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.layout_article_list.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.adapter.ArticleAdapter
import me.robbin.wanandroid.ui.adapter.PagingLoadStateAdapter
import me.robbin.wanandroid.viewmodel.ArticleListViewModel

/**
 *
 * Create by Robbin at 2020/7/21
 */
class ArticleListsFragment : BaseVMFragment<ArticleListViewModel>() {

    override fun getLayoutRes(): Int = R.layout.layout_article_list

    private var type: ArticleType = ArticleType.HOME
    private var cid: Int = -1

    private val articleAdapter by lazy { ArticleAdapter(requireContext()) }

    private var articleJob: Job? = null
    private lateinit var refresh: SwipeRefreshLayout

    override fun initView(savedInstanceState: Bundle?) {
        arguments?.let {
            type = it.getSerializable("type") as ArticleType
            cid = it.getInt("cid")
        }
        rlArticles.adapter = articleAdapter
        initAdapter()
        refresh = refreshArticles
        refresh.setOnRefreshListener { refreshData() }
        btnError.setOnClickListener { articleAdapter.retry() }
    }

    override fun initData() {
        articleJob?.cancel()
        articleJob = lifecycleScope.launchWhenResumed {
            mViewModel.getArticleList(type, cid).collect {
                articleAdapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        rlArticles.adapter =
            articleAdapter.withLoadStateFooter(PagingLoadStateAdapter { articleAdapter.retry() })
        articleAdapter.setOnArticleItemClickListener(object :
            ArticleAdapter.OnArticleItemClickListener {
            override fun setNavController(): NavController = nav()
        })
        articleAdapter.addLoadStateListener { loadState ->
            rlArticles.isVisible = loadState.refresh is LoadState.NotLoading
            loadingArticles.isVisible = loadState.refresh is LoadState.Loading
            ivError.isVisible = loadState.refresh is LoadState.Error
            btnError.isVisible = loadState.refresh is LoadState.Error
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                "\uD83D\uDE28 Wooops: ${it.error}".toToast()
            }
        }
    }

    private fun refreshData() {
        articleJob?.cancel()
        articleJob = lifecycleScope.launch {
            mViewModel.getArticleList(type, cid).collect {
                refresh.isRefreshing = false
                articleAdapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        articleJob?.cancel()
    }

    companion object {

        fun newInstance(type: ArticleType, cid: Int = -1): ArticleListsFragment {
            val args = Bundle()
            args.putSerializable("type", type)
            args.putInt("cid", cid)
            val fragment = ArticleListsFragment()
            fragment.arguments = args
            return fragment
        }

    }

}

enum class ArticleType {
    HOME, QUESTION, SHARE, TREE, PROJECT, LAST_PROJECT, PUBLIC
}