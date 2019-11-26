package com.win.jetpackdemo.room.dao

import androidx.room.*

import com.win.jetpackdemo.room.entity.Bill
import com.win.jetpackdemo.room.entity.BillFullInfo
import com.win.jetpackdemo.room.entity.BillSimpleInfo

/**
 * Author：win
 * Date：2019-11-13
 */
@Dao
interface BillDao {

    @Query("SELECT bill.fid, bill.type, bill.money, bill.memo, bill.pictureName, " +
            "bill.tradeTime, bill.tagName, bill.tagType, bill.tagIcon," +
            "account.fid as accountId, account.name as accountName, account.groupType as accountGroup " +
            "FROM t_bill AS bill " +
            "INNER JOIN t_account as account ON bill.accountId = account.fid ")
    fun getBillDetailList(): List<BillFullInfo>

    @Query("SELECT fid, type, money, memo, tradeTime FROM t_bill ORDER BY tradeTime DESC")
    fun getBillSimpleList(): List<BillSimpleInfo>?

    @Query("SELECT * FROM t_bill ORDER BY tradeTime DESC")
    fun getAllBill(): List<Bill>?

    @Query("SELECT * FROM t_bill WHERE tradeTime >= :begin and tradeTime <= :end ORDER BY tradeTime DESC")
    fun getBillBetween(begin: Long, end: Long): List<Bill>?

    @Query("SELECT * FROM t_bill WHERE accountId = :accountId ORDER BY tradeTime DESC")
    fun getBillByAccount(accountId: Long): List<Bill>?

    @Insert(entity = Bill::class, onConflict = OnConflictStrategy.IGNORE)
    fun addBill(bill: Bill)

    @Insert(entity = Bill::class, onConflict = OnConflictStrategy.IGNORE)
    fun addBills(billList: List<Bill>)

    @Query("DELETE FROM t_bill WHERE fid = :id")
    fun deleteBillById(id: Long)

    @Query("DELETE FROM t_bill")
    fun deleteAllBill()

    @Update(entity = Bill::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateBill(bill: Bill)

//    @Transaction
//    fun updateBill
}