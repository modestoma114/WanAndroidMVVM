package me.robbin.wanandroid.app.event.listener

import androidx.navigation.NavController

/**
 *
 * Create by Robbin at 2020/7/30
 */
interface AdapterItemClickListener {

    fun itemClickListener(): NavController

    fun itemLongClickListener(position: Int)

}