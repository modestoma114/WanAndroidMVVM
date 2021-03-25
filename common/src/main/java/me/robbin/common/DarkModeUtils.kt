package me.robbin.common

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate

fun isNightMode(context: Context): Boolean {
    val mode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return mode == Configuration.UI_MODE_NIGHT_YES
}

fun setNightMode(isNightMode: Boolean) {
    AppCompatDelegate.setDefaultNightMode(
        if (isNightMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
    )
}