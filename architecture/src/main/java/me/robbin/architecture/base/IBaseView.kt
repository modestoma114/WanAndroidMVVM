package me.robbin.architecture.base

import android.os.Bundle

interface IBaseView {

    fun initView(savedInstanceState: Bundle?)

    fun initData()

    fun createObserver()

}