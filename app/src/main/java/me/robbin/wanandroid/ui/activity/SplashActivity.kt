package me.robbin.wanandroid.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.view.main.MainActivity

/**
 * 启动页
 * Create by Robbin at 2020/8/4
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val motionLayout = findViewById<MotionLayout>(R.id.motion_layout)
        lifecycleScope.launchWhenCreated {
            delay(500)
            motionLayout.transitionToEnd()
            delay(1000)
            MainActivity.startMain(this@SplashActivity)
            finish()
        }
    }

}