package com.win.jetpackdemo.room.dao

import androidx.room.*
import com.win.jetpackdemo.room.entity.Account

import com.win.jetpackdemo.room.entity.AccountWithBill

/**
 * Author：win
 * Date：2019-11-13
 */
@Dao
interface AccountDao {

    @Query("SELECT * FROM t_account")
    fun getAllAccount(): List<Account>?

    @Query("SELECT * FROM t_account WHERE fid = :id ")
    fun getAccountById(id: Long): Account?

    @Insert(entity = Account::class, onConflict = OnConflictStrategy.IGNORE)
    fun addAccount(account: Account)

    @Query("DELETE FROM t_account WHERE fid = :id ")
    fun deleteAccountById(id: Long)

    @Query("DELETE FROM t_account")
    fun deleteAllAccount()

    @Update(entity = Account::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateAccount(account: Account)

    @Transaction
    @Query("SELECT * FROM t_account WHERE fid = :accountId ")
    fun getAccountWithBill(accountId: Long): List<AccountWithBill>?
}