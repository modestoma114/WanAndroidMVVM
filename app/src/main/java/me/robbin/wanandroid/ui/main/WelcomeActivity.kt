package me.robbin.wanandroid.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import me.robbin.wanandroid.R

/**
 * 启动页
 * @author Created by Robbin in 2021/03/30
 */
class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val motionLayout = findViewById<MotionLayout>(R.id.motion_layout)
        lifecycleScope.launchWhenCreated {
            delay(500)
            motionLayout.transitionToEnd()
            delay(1000)
            MainActivity.start(this@WelcomeActivity)
            finish()
        }
    }

}