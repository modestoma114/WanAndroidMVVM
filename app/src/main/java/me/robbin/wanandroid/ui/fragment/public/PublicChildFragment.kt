package me.robbin.wanandroid.ui.fragment.public

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_list_article.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import me.robbin.mvvmscaffold.base.fragment.BaseDBFragment
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentListArticleBinding
import me.robbin.wanandroid.ui.adapter.ArticleAdapter
import me.robbin.wanandroid.viewmodel.PublicChildViewModel

/**
 *
 * Create by Robbin at 2020/7/13
 */
class PublicChildFragment: BaseDBFragment<PublicChildViewModel, FragmentListArticleBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_list_article

    private var cid = 0
    private var articleJob: Job? = null

    private val adapter by lazy { ArticleAdapter() }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        arguments?.let {
            cid = it.getInt("cid")
        }
        rlListArticle.adapter = adapter
    }

    override fun initData() {
        articleJob = lifecycleScope.launchWhenCreated {
            mViewModel.getArticle(cid).collect {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        articleJob?.cancel()
    }

    companion object {
        fun newInstance(cid: Int): PublicChildFragment {
            val args = Bundle()
            args.putInt("cid", cid)
            val fragment = PublicChildFragment()
            fragment.arguments = args
            return fragment
        }
    }

}