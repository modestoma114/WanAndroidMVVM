package me.robbin.wanandroid.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.delay
import me.robbin.wanandroid.R

/**
 * 启动页
 * Create by Robbin at 2020/8/4
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launchWhenCreated {
            delay(500)
            motion_layout.transitionToEnd()
            delay(1000)
            MainActivity.startMain(this@SplashActivity)
            finish()
        }
    }

}