package me.robbin.wanandroid.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import me.robbin.wanandroid.R
import me.robbin.wanandroid.ui.home.HomeFragment

/**
 * 主界面
 * @author Created by Robbin in 2021/03/30
 */
class MainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, homeFragment).commit()
    }

}