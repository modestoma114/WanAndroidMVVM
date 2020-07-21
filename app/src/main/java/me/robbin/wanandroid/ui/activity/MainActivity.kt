package me.robbin.wanandroid.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.robbin.mvvmscaffold.ext.viewmodel.getAppVM
import me.robbin.mvvmscaffold.utils.setStatusBarTransparent
import me.robbin.mvvmscaffold.utils.toToast
import me.robbin.wanandroid.R
import me.robbin.wanandroid.viewmodel.AppViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBarTransparent()
    }
}