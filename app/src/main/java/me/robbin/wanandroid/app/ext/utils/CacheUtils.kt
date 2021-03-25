package me.robbin.wanandroid.app.ext.utils

import android.text.TextUtils
import com.tencent.mmkv.MMKV
import me.robbin.net.fromJson
import me.robbin.net.toJson
import me.robbin.wanandroid.model.User

/**
 * 本地缓存工具
 * Create by Robbin at 2020/7/22
 */
object CacheUtils {

    private val sp = MMKV.mmkvWithID("wandroid")

    /**
     * 判断用户是否登录
     * Create by Robbin at 2020/7/22
     */
    fun isLogin(): Boolean {
        return sp?.decodeBool("isLogin", false) ?: false
    }

    /**
     * 设置用户登录状态
     * @param isLogin 登陆状态
     * Create by Robbin at 2020/7/22
     */
    fun setIsLogin(isLogin: Boolean) {
        sp?.encode("isLogin", isLogin)
    }

    /**
     * 获取登录用户信息
     * @return 本地缓存的用户信息
     * Create by Robbin at 2020/7/22
     */
    fun getUser(): User? {
        return sp?.decodeString("userInfo")?.fromJson()
    }

    /**
     * 设置用户信息
     * @param user 要保存的用户信息
     * Create by Robbin at 2020/7/22
     */
    fun setUser(user: User?) {
        if (user == null) {
            sp?.encode("userInfo", "")
            setIsLogin(false)
        } else {
            sp?.encode("userInfo", user.toJson())
            setIsLogin(true)
        }
    }

    /**
     * 存储夜间模式状态
     * Create by Robbin at 2020/7/29
     */
    fun setNightMode(isNightMode: Boolean) {
        sp?.encode("key_night_mode", isNightMode)
    }

    /**
     * 获得夜间模式设置
     * Create by Robbin at 2020/7/29
     */
    fun getNightMode(): Boolean {
        return sp?.decodeBool("key_night_mode", false) ?: false
    }

}