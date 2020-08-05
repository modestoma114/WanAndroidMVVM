package me.robbin.wanandroid.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.robbin.mvvmscaffold.utils.setStatusBarTransparent
import me.robbin.wanandroid.R

/**
 * App 主 Activity
 * Create by Robbin at 2020/7/10
 */
class MainActivity : AppCompatActivity() {

    companion object {
        fun startMain(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 设置状态栏沉浸
        setStatusBarTransparent()
    }
}