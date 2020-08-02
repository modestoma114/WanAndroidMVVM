package me.robbin.wanandroid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 分类
 * Created by Robbin on 2020-07-13
 */
@Parcelize
data class ChapterBean(
    var courseId: Int,
    var id: Int,
    var name: String,
    var order: Int,
    var parentChapterId: Int,
    var userControlSetTop: Boolean,
    var visible: Int,
    var children: List<ChapterBean>
): Parcelable