package me.robbin.wanandroid.app

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process
import com.tencent.mmkv.MMKV
import me.robbin.common.setNightMode
import me.robbin.wanandroid.app.ext.utils.CacheUtils

/**
 * 自定义 Application 类
 * Create by Robbin at 2020/7/17
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        // 初始化MMKV
        MMKV.initialize(this)
        if (isMainProcess(this))
            init()
    }

    private fun init() {
        setDayNightMode()
    }

    private fun setDayNightMode() {
        setNightMode(CacheUtils.getNightMode())
    }

}

/**
 * 是否是主进程
 */
fun isMainProcess(context: Context) = context.packageName == currentProcessName(context)

/**
 * 当前进程的名称
 */
private fun currentProcessName(context: Context): String {
    val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (process in manager.runningAppProcesses) {
        if (process.pid == Process.myPid()) {
            return process.processName
        }
    }
    return ""
}