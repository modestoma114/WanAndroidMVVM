package me.robbin.wanandroid.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import me.robbin.mvvmscaffold.ext.view.setStatusBarColor
import me.robbin.mvvmscaffold.ext.view.setStatusBarTransparent
import me.robbin.wanandroid.ui.fragment.MainFragment
import me.robbin.wanandroid.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            add<MainFragment>(R.id.container_main)
        }
        setStatusBarTransparent()
    }
}