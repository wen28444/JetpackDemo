package com.win.jetpackdemo.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author：win
 * Date：2019-11-13
 */
@Entity(tableName = "t_account")
data class Account(
    @PrimaryKey
    @ColumnInfo(name = "fid")
    val id: Long,
    val name: String,
    val groupType: String
) {
    var createTime: Long = 0
    var updateTime: Long = 0

    override fun toString(): String {
        return "account[id:$id, name:$name, groupType:$groupType]"
    }

    companion object {
        const val GROUP_ASSETS = "assets"
        const val GROUP_DEBT = "debt"
    }
}