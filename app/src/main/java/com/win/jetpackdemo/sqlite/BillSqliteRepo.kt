package com.win.jetpackdemo.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

/**
 * Author：win
 * Date：2019-11-14
 */
class BillSqliteRepo(context: Context, dbPath: String? = null) {

    private val billSqliteHelper by lazy {
        BillSqliteHelper.getDbHelper(context.applicationContext, dbPath)
    }

    fun insertAccount(accountEntity: AccountEntity) {
        val sqliteDatabase = billSqliteHelper.readableDatabase ?: return

        val contentValues = ContentValues().apply {
            put("fid", accountEntity.id)
            put("name", accountEntity.name)
            put("groupType", accountEntity.group)
        }
        sqliteDatabase.insertWithOnConflict("t_account", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE)
    }

    fun getAccount(accountId: Long): AccountEntity? {
        val sqliteDatabase = billSqliteHelper.readableDatabase ?: return null

        val cursor = sqliteDatabase.rawQuery("SELECT * FROM t_account WHERE fid = ?", arrayOf(accountId.toString()))
        cursor.use {
            if (it.moveToNext()) {
                return AccountEntity(
                    cursor.getLong(cursor.getColumnIndex("fid")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("groupType"))
                )
            }
        }
        return null
    }

    fun insertBill(billList: List<BillEntity>) {
        val sqliteDatabase = billSqliteHelper.readableDatabase ?: return

        try {
            sqliteDatabase.beginTransaction()

            for (bill in billList) {
                val contentValues = ContentValues().apply {
                    put("fid", bill.id)
                    put("type", bill.type)
                    put("money", bill.money)
                    put("memo", bill.memo)
                    put("pictureName", bill.pictureName)
                    put("accountId", bill.accountEntity.id)
                    put("tagName", bill.tagEntity?.tagName)
                    put("tagType", bill.tagEntity?.tagType)
                    put("tagIcon", bill.tagEntity?.tagIcon)
                    put("tradeTime", bill.tradeTime)
                    put("createTime", bill.createTime)
                    put("updateTime", bill.updateTime)
                }
                sqliteDatabase.insertWithOnConflict("t_bill", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE)
            }

            sqliteDatabase.setTransactionSuccessful()
        } finally {
            sqliteDatabase.endTransaction()
        }
    }

    fun getBillList(): List<BillEntity>? {
        val sqliteDatabase = billSqliteHelper.readableDatabase ?: return null

        val sql = "SELECT bill.fid, bill.type, bill.money, bill.memo, bill.pictureName, bill.tagName, bill.tagType, bill.tagIcon, bill.tradeTime, " +
                "account.fid as accountId, account.name as accountName, account.groupType as accountGroup " +
                "FROM t_bill as bill " +
                "INNER JOIN t_account as account ON bill.accountId = account.fid " +
                "ORDER BY bill.tradeTime DESC "
        val cursor = sqliteDatabase.rawQuery(sql, null)
        cursor.use {
            val billList = ArrayList<BillEntity>(it.count)
            while (it.moveToNext()) {
                val billEntity = BillEntity().apply {
                    id = cursor.getLong(cursor.getColumnIndex("fid"))
                    type = cursor.getInt(cursor.getColumnIndex("type"))
                    money = cursor.getDouble(cursor.getColumnIndex("money"))
                    memo = cursor.getString(cursor.getColumnIndex("memo"))
                    pictureName = cursor.getString(cursor.getColumnIndex("pictureName"))
                    tradeTime = cursor.getLong(cursor.getColumnIndex("tradeTime"))

                    val tagEntity = TagEntity(
                        cursor.getString(cursor.getColumnIndex("tagName")),
                        cursor.getInt(cursor.getColumnIndex("tagType")),
                        cursor.getString(cursor.getColumnIndex("tagIcon"))
                    )

                    val accountEntity = AccountEntity(
                        cursor.getLong(cursor.getColumnIndex("accountId")),
                        cursor.getString(cursor.getColumnIndex("accountName")),
                        cursor.getString(cursor.getColumnIndex("accountGroup"))
                    )
                    this.tagEntity = tagEntity
                    this.accountEntity = accountEntity
                }
                billList.add(billEntity)
            }
            return billList
        }
    }
}