package me.robbin.wanandroid.app.event

import androidx.lifecycle.ViewModel
import me.robbin.mvvmscaffold.fix.livedata.UnPeekLiveData

/**
 *
 * Create by Robbin at 2020/8/2
 */
class SharedViewModel : ViewModel() {

    val userCollectUpdate: UnPeekLiveData<Int> = UnPeekLiveData()

}