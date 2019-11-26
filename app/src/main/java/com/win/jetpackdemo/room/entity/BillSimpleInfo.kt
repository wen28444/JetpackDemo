package com.win.jetpackdemo.room.entity

import androidx.room.ColumnInfo

/**
 * Author：win
 * Date：2019-11-14
 */
data class BillSimpleInfo(
    @ColumnInfo(name = "fid")
    val id: Long,
    val type: Int,
    val money: Double,
    val memo: String,
    val tradeTime: Long
)