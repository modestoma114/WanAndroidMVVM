package me.robbin.wanandroid.ui.fragment.me

import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.flow.collect
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.databinding.FragmentProfileBinding
import me.robbin.wanandroid.ui.adapter.ArticleAdapter
import me.robbin.wanandroid.viewmodel.me.ProfileViewModel

/**
 *
 * Create by Robbin at 2020/7/21
 */
class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    private val articleAdapter by lazy { ArticleAdapter(requireContext()) }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_profile, BR.viewModel, mViewModel)
    }

    override fun initData() {
        rlTest.adapter = articleAdapter
        lifecycleScope.launchWhenCreated {
            mViewModel.getArticle().collect {
                articleAdapter.submitData(it)
            }
        }
    }

}