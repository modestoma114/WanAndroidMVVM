package me.robbin.wanandroid.app

import com.tencent.mmkv.MMKV
import me.robbin.mvvmscaffold.base.BaseApplication

/**
 * 自定义 Application 类
 * Create by Robbin at 2020/7/17
 */
class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        // 初始化MMKV
        MMKV.initialize(this)
    }

}