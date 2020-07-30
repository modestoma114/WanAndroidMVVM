package me.robbin.wanandroid.ui.fragment.knowledge.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import me.robbin.wanandroid.app.base.BaseVM
import me.robbin.wanandroid.app.watcher.SimpleWatcher
import me.robbin.wanandroid.data.api.ApiService

/**
 *
 * Create by Robbin at 2020/7/30
 */
class ShareArticleViewModel : BaseVM() {

    val articleTitle: MutableLiveData<String> = MutableLiveData("")
    val articleUrl: MutableLiveData<String> = MutableLiveData("")
    val author: MutableLiveData<String> = MutableLiveData("分享人: ")

    val titleWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            articleTitle.value = s.toString()
        }
    }

    val urlWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            articleUrl.value = s.toString()
        }
    }

    fun shareArticle(success: () -> Unit) {
        if (checkUrl()) {
            launchOnlyResult(
                block = {
                    ApiService.getApi().shareArticle(articleTitle.value!!, articleUrl.value!!)
                },
                success = { success() }
            )
        }
    }

    private fun checkUrl(): Boolean {
        return articleTitle.value.toString().isNotBlank()
                && articleUrl.value.toString().isNotBlank()
    }

}