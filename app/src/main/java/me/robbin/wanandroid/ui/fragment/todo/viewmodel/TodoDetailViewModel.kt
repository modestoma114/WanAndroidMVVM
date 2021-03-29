package me.robbin.wanandroid.ui.fragment.todo.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import me.robbin.mvvmscaffold.base.viewmodel.BaseViewModel
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.app.ext.watcher.SimpleWatcher
import me.robbin.wanandroid.model.Todo

/**
 *
 * Create by Robbin at 2020/8/1
 */
class TodoDetailViewModel : BaseViewModel() {

    val title: MutableLiveData<String> = MutableLiveData("")
    val content: MutableLiveData<String> = MutableLiveData("")
    val dateStr: MutableLiveData<String> = MutableLiveData("")
    val type: MutableLiveData<Int> = MutableLiveData(1)
    val priority: MutableLiveData<Int> = MutableLiveData(1)
    val id: MutableLiveData<Int> = MutableLiveData(-1)

    val bean: MutableLiveData<Todo> = MutableLiveData()

    val priorityStr: MutableLiveData<String> = MutableLiveData("普通")
    val typeStr: MutableLiveData<String> = MutableLiveData("工作")

    val titleWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            title.value = s.toString()
        }
    }

    val detailWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            content.value = s.toString()
        }
    }

    fun setDate(dateStr: String) {
        this.dateStr.value = dateStr
    }

    fun setPriority(priority: Int) {
        this.priority.value = priority
        this.priorityStr.value = when (priority) {
            1 -> "普通"
            else -> "紧急"
        }
    }

    fun setType(type: Int) {
        this.type.value = type
        this.typeStr.value = when (type) {
            1 -> "工作"
            2 -> "日常"
            3 -> "娱乐"
            else -> "其他"
        }
    }

    fun setValue(bean: Todo) {
        title.value = bean.title
        content.value = bean.content
        dateStr.value = bean.dateStr
        type.value = bean.type
        priority.value = bean.priority
        id.value = bean.id
        this.bean.value = bean
    }

    fun addTodo(success: () -> Unit) {
        if (checkDetail()) {
            launchOnlyResult(
                block = {
                    ApiService.getApi()
                        .addTodo(
                            title.value!!,
                            content.value!!,
                            dateStr.value!!,
                            type.value!!,
                            priority.value!!
                        )
                },
                success = { success() }
            )
        }
    }

    fun updateTodo(success: (bean: Todo) -> Unit) {
        if (checkDetail()) {
            launchOnlyResult(
                block = {
                    bean.value!!.run {
                        this.title = this@TodoDetailViewModel.title.value!!
                        this.content = this@TodoDetailViewModel.content.value!!
                        this.dateStr = this@TodoDetailViewModel.dateStr.value!!
                        this.type = this@TodoDetailViewModel.type.value!!
                        this.priority = this@TodoDetailViewModel.priority.value!!
                    }
                    ApiService.getApi().updateTodo(
                        title.value!!,
                        content.value!!,
                        dateStr.value!!,
                        type.value!!,
                        priority.value!!,
                        id.value!!
                    )
                },
                success = { success(bean.value!!) }
            )
        }
    }

    private fun checkDetail(): Boolean {
        return !title.value.isNullOrBlank() && !dateStr.value.isNullOrBlank()
    }

}