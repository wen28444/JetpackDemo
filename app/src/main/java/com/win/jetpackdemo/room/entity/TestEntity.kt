package com.win.jetpackdemo.room.entity

import androidx.room.Entity

/**
 * Author：win
 * Date：2019-11-13
 */
@Entity(tableName = "t_test")
data class TestEntity(val id: Long, val key: String, val value: String)