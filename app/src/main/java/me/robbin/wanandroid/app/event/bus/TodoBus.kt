package me.robbin.wanandroid.app.event.bus

import me.robbin.wanandroid.model.Todo

/**
 *
 * Create by Robbin at 2020/8/5
 */
data class TodoBus(var id: Int, var status: Int)

data class TodoDetailBus(var id: Int, var bean: Todo)