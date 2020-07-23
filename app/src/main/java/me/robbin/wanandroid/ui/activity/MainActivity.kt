package me.robbin.wanandroid.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.robbin.mvvmscaffold.utils.setStatusBarTransparent
import me.robbin.wanandroid.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBarTransparent()
    }
}