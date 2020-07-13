package me.robbin.wanandroid.data.repository

import androidx.paging.PagingSource
import me.robbin.wanandroid.data.api.ApiService
import me.robbin.wanandroid.data.bean.ChapterBean

/**
 *
 * Create by Robbin at 2020/7/13
 */
class WechatRepository {

    suspend fun getWechatList() = ApiService.getApi().getWechatList()

}

//class WecharPagingSource: PagingSource<Int, ChapterBean>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChapterBean> {
//        return try {
//            val result = ApiService.getApi()
//        }
//    }
//
//}