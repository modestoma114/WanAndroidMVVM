package me.robbin.wanandroid.app.utils

import android.text.TextUtils
import com.google.gson.Gson
import com.tencent.mmkv.MMKV
import me.robbin.wanandroid.data.bean.UserBean

/**
 *
 * Create by Robbin at 2020/7/22
 */
object CacheUtils {

    private val sp = MMKV.mmkvWithID("app")

    /**
     * 判断用户是否登录
     * Create by Robbin at 2020/7/22
     */
    fun isLogin(): Boolean {
        return sp.decodeBool("isLogin", false)
    }

    /**
     * 设置用户登录状态
     * Create by Robbin at 2020/7/22
     */
    fun setIsLogin(isLogin: Boolean) {
        sp.encode("isLogin", isLogin)
    }

    /**
     * 获取登录用户信息
     * Create by Robbin at 2020/7/22
     */
    fun getUser(): UserBean? {
        val user = sp.decodeString("userInfo")
        return if (TextUtils.isEmpty(user)) null
        else Gson().fromJson(user, UserBean::class.java)
    }

    /**
     * 设置用户信息
     * Create by Robbin at 2020/7/22
     */
    fun setUser(user: UserBean?) {
        if (user == null) {
            sp.encode("userInfo", "")
            setIsLogin(false)
        } else {
            sp.encode("userInfo", Gson().toJson(user))
            setIsLogin(true)
        }
    }

}