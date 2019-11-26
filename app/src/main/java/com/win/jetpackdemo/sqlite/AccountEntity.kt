package com.win.jetpackdemo.sqlite

/**
 * Author：win
 * Date：2019-11-14
 */
data class AccountEntity(
    val id: Long,
    val name: String,
    val group: String) {

    override fun toString(): String {
        return "account[id:$id, name:$name, group:$group]"
    }

    companion object {
        const val GROUP_ASSETS = "assets"
        const val GROUP_DEBT = "debt"
    }
}