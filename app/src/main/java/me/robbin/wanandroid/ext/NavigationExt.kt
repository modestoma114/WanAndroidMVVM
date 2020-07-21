package me.robbin.wanandroid.ext

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import me.robbin.mvvmscaffold.navigation.NavHostFragment

/**
 *
 * Create by Robbin at 2020/7/13
 */
fun Fragment.nav(): NavController {
    return NavHostFragment.findNavController(this)
}

//fun NavController.goLogin(action: (NavController) -> Unit) {
//    if (AppCache.isLogin()) {
//        navigate(action)
//    } else {
//        navigate(R.id.action_global_loginFragment)
//    }
//}