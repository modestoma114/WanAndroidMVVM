package me.robbin.wanandroid.ui.home

import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.robbin.architecture.base.BaseFragment
import me.robbin.architecture.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentHomeBinding
import me.robbin.wanandroid.ui.common.ArticlesAdapter

class HomeFragment: BaseFragment<HomeStateViewModel, FragmentHomeBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            layout = R.layout.fragment_home,
            vmVariableId = BR.state,
            stateViewModel = mViewModel
        ).addBindingParams(BR.articlesAdapter, mViewModel.adapter)
    }

    private var articleJob: Job? = null

    override fun initData() {
        articleJob = lifecycleScope.launch {
            mViewModel.homeArticles().collectLatest {
                Log.d("HomeFragment", it.toString())
                mViewModel.adapter.submitData(it)
            }
        }
    }

}