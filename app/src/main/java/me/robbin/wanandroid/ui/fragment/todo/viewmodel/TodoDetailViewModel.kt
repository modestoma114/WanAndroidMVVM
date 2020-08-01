package me.robbin.wanandroid.ui.fragment.todo.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import me.robbin.wanandroid.app.base.BaseVM
import me.robbin.wanandroid.app.watcher.SimpleWatcher
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.TodoBean

/**
 *
 * Create by Robbin at 2020/8/1
 */
class TodoDetailViewModel : BaseVM() {

    val bean: MutableLiveData<TodoBean> = MutableLiveData()

    val titleWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            bean.value?.title = s.toString()
        }
    }

    val detailWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            bean.value?.content = s.toString()
        }
    }

    fun deleteTodo(success: () -> Unit) {
        launchOnlyResult(
            block = { ApiService.getApi().deleteTodo(bean.value!!.id) },
            success = { success() }
        )
    }

    fun updateTodo(success: () -> Unit) {
        val bean = bean.value!!
        launchOnlyResult(
            block = {
                ApiService.getApi().updateTodo(
                    bean.title,
                    bean.content,
                    bean.dateStr,
                    bean.type,
                    bean.priority,
                    bean.id
                )
            },
            success = { success() }
        )
    }

}