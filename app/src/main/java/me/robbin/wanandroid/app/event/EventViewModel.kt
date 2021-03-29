package me.robbin.wanandroid.app.event

import androidx.lifecycle.ViewModel
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import me.robbin.wanandroid.app.event.bus.CollectBus
import me.robbin.wanandroid.app.event.bus.TodoBus
import me.robbin.wanandroid.app.event.bus.TodoDetailBus

/**
 *
 * Create by Robbin at 2020/8/2
 */
class EventViewModel : ViewModel() {

    val userCollectUpdate: UnPeekLiveData<CollectBus> = UnPeekLiveData()

    val changeTodoStatus: UnPeekLiveData<TodoBus> = UnPeekLiveData()

    val deleteTodo: UnPeekLiveData<TodoBus> = UnPeekLiveData()

    val addTodo: UnPeekLiveData<Boolean> = UnPeekLiveData()

    val changeTodo: UnPeekLiveData<TodoDetailBus> = UnPeekLiveData()

}