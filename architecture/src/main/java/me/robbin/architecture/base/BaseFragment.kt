package me.robbin.architecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import me.robbin.architecture.ext.getVMCls
import me.robbin.architecture.model.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel, VDB : ViewDataBinding> : Fragment(), IBaseView {

    protected lateinit var mViewModel: VM
    private lateinit var mBinding: VDB

    protected abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(getVMCls(this))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            DataBindingUtil.inflate(inflater, getDataBindingConfig().getLayout(), container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.setVariable(
            getDataBindingConfig().getVmVariableId(),
            getDataBindingConfig().getStateViewModel()
        )
        getDataBindingConfig().getBindingParams().forEach { key, value ->
            mBinding.setVariable(key, value)
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        initView(savedInstanceState)
        initData()
        createObserver()
    }

    override fun initView(savedInstanceState: Bundle?) {}

    override fun initData() {}

    override fun createObserver() {}

}