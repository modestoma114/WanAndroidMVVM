package me.robbin.wanandroid.ext

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import me.robbin.wanandroid.R
import me.robbin.wanandroid.viewmodel.AppViewModel

/**
 *
 * Create by Robbin at 2020/7/21
 */
fun Fragment.checkLogin(viewModel: AppViewModel, action: () -> Unit) {

    if (viewModel.isLogin.value == true) {
        action()
    } else {
        nav().navigate(R.id.action_global_loginFragment)
    }

}

fun Fragment.backMain(viewModel: AppViewModel) {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
        viewModel.isLogin.value = false
        nav().popBackStack(R.id.containerFragment, false)
    }
}