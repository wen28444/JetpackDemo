package com.win.jetpackdemo.room.entity

import androidx.room.ColumnInfo

/**
 * Author：win
 * Date：2019-11-13
 */
class Tag {
    @ColumnInfo(name = "tagName")
    var name: String = ""
    @ColumnInfo(name = "tagType")
    var type: Int = 0
    @ColumnInfo(name = "tagIcon")
    var icon: String = ""

    override fun toString(): String {
        return "tag[name:$name, type:$type, icon:$icon]"
    }

    companion object {
        const val TYPE_CATEGORY_PAYOUT = 0
        const val TYPE_CATEGORY_INCOME = 1
    }
}