package com.win.jetpackdemo.sqlite

/**
 * Author：win
 * Date：2019-11-14
 */
data class TagEntity(
    val tagName: String,
    val tagType: Int,
    val tagIcon: String) {

    override fun toString(): String {
        return "tag[name:$tagName, type:$tagType, icon:$tagIcon]"
    }

    companion object {
        const val TYPE_CATEGORY_PAYOUT = 0
        const val TYPE_CATEGORY_INCOME = 1
    }
}