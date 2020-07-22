package me.robbin.wanandroid.app

import com.tencent.mmkv.MMKV
import me.robbin.mvvmscaffold.base.BaseApplication

/**
 *
 * Create by Robbin at 2020/7/17
 */
class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
    }

}