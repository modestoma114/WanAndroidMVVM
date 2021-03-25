package me.robbin.architecture.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import me.robbin.architecture.ext.getVMCls
import me.robbin.architecture.model.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel, VDB : ViewDataBinding> : AppCompatActivity(),
    IBaseView {

    protected lateinit var mViewModel: VM
    private lateinit var mBinding: VDB

    protected abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(getVMCls(this))
        mBinding = DataBindingUtil.setContentView(this, getDataBindingConfig().getLayout())
        mBinding.lifecycleOwner = this
        mBinding.setVariable(
            getDataBindingConfig().getVmVariableId(),
            getDataBindingConfig().getStateViewModel()
        )
        getDataBindingConfig().getBindingParams().forEach { key, value ->
            mBinding.setVariable(key, value)
        }
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