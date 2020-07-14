package me.robbin.wanandroid.ui.fragment.common

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_list_article.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ui.adapter.ArticleAdapter
import me.robbin.wanandroid.viewmodel.ChapterChildViewModel

/**
 *
 * Create by Robbin at 2020/7/14
 */
class ChapterChildFragment: BaseVMFragment<ChapterChildViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_list_article

    private var type: Int = 1

    private val adapter by lazy { ArticleAdapter() }

    private var articleJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        arguments?.let {
            type = it.getInt("type")
        }
        rlListArticle.adapter = adapter
    }

    override fun initData() {
        articleJob = lifecycleScope.launchWhenCreated {
            mViewModel.getQuestion(type).collect {
                adapter.submitData(it)
            }
        }
    }

    companion object {
        fun newInstance(type: Int): ChapterChildFragment {
            val args = Bundle()
            args.putInt("type", type)
            val fragment =
                ChapterChildFragment()
            fragment.arguments = args
            return fragment
        }
    }

}