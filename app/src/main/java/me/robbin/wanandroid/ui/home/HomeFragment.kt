package me.robbin.wanandroid.ui.home

import me.robbin.architecture.base.BaseFragment
import me.robbin.architecture.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<HomeStateViewModel, FragmentHomeBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            layout = R.layout.fragment_home,
            vmVariableId = BR.state,
            stateViewModel = mViewModel
        )
    }

}