package me.robbin.wanandroid.ui.fragment.todo.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.data.repository.TodoRepository

/**
 * TodoL 界面 ViewModel
 * Create by Robbin at 2020/7/27
 */
class TodoViewModel : BaseViewModel() {

    private val todoRepository by lazy { TodoRepository.instance }

    fun getTodoList() = todoRepository.getTodoList(0)

    fun getDoneTodoList() = todoRepository.getTodoList(1)

    fun doneTodo(id: Int, status: Int, success: () -> Unit) {
        launchOnlyResult(
            block = { ApiService.getApi().doneTodo(id, status) },
            success = { success() }
        )
    }

    fun deleteTodo(id: Int, success: () -> Unit) {
        launchOnlyResult(
            block = { ApiService.getApi().deleteTodo(id) },
            success = { success() }
        )
    }

}