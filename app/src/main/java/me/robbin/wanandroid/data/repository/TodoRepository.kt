package me.robbin.wanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import me.robbin.wanandroid.data.datasource.TodoDataSource

/**
 * Todo 仓库
 * Create by Robbin at 2020/7/27
 */
class TodoRepository private constructor() {

    companion object {
        val instance: TodoRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            TodoRepository()
        }
    }

    /**
     * 获取 Todo 列表
     * Create by Robbin at 2020/7/27
     */
    fun getTodoList() =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            TodoDataSource()
        }.flow

}