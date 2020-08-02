package me.robbin.wanandroid.app.ext

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.utils.CacheUtils

/**
 * Fragment 拓展函数
 * Create by Robbin at 2020/7/21
 */

/**
 * Fragment 中检查登录状态
 * @param action 原先要执行的行为
 * Create by Robbin at 2020/7/21
 */
fun Fragment.checkLogin(action: () -> Unit) {

    if (CacheUtils.isLogin()) {
        action()
    } else {
        nav().navigate(R.id.action_global_loginFragment)
    }

}

/**
 * 直接从当前 Fragment 返回 MainFragment
 * Create by Robbin at 2020/7/21
 */
fun Fragment.backMain() {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
        CacheUtils.setIsLogin(false)
        nav().popBackStack(R.id.containerFragment, false)
    }
}