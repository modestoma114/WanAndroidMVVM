package me.robbin.wanandroid.ui.fragment.todo.viewmodel

import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.repository.TodoRepository

/**
 * TodoL 界面 ViewModel
 * Create by Robbin at 2020/7/27
 */
class TodoViewModel : BaseViewModel() {

    private val todoRepository by lazy { TodoRepository.instance }

    fun getTodoList() = todoRepository.getTodoList()

    fun addTodo(
        title: String,
        content: String,
        date: String,
        type: Int,
        priority: Int,
        success: () -> Unit
    ) {
        launchOnlyResult(
            block = { ApiService.getApi().addTodo(title, content, date, type, priority) },
            success = { success() }
        )
    }

    fun doneTodo(id: Int, status: Int, error: () -> Unit) {
        launchOnlyResult(
            block = { ApiService.getApi().doneTodo(id, status) },
            success = {  },
            error = { error() }
        )
    }

}