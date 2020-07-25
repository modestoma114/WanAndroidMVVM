package me.robbin.wanandroid.ext

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.utils.CacheUtils

/**
 *
 * Create by Robbin at 2020/7/21
 */
fun Fragment.checkLogin(action: () -> Unit) {

    if (CacheUtils.isLogin()) {
        action()
    } else {
        nav().navigate(R.id.action_global_loginFragment)
    }

}

fun Fragment.backMain() {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
        CacheUtils.setIsLogin(false)
        nav().popBackStack(R.id.containerFragment, false)
    }
}