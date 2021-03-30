package me.robbin.wanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import me.robbin.wanandroid.api.WanService
import me.robbin.wanandroid.data.datesource.ArticleDataSource

object ArticleRepository {

    fun homeArticles() =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            ArticleDataSource(WanService.getApi())
        }.flow

}