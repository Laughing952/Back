package com.laughing.kotlin.anko.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.laughing.kotlin.anko.constant.ShopCarTable
import org.jetbrains.anko.db.*

/**
 * 使用anko提供的ManagedSQLiteOpenHelper
 * 作者：Laughing on 2018/6/15 10:36
 * 邮箱：719240226@qq.com
 */
class DBOpenHelper(mContext: Context) : ManagedSQLiteOpenHelper(mContext, ShopCarTable.DB_NAME, null, ShopCarTable.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(ShopCarTable.TABLE_NAME, true,
                ShopCarTable._ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ShopCarTable.NAME to TEXT,
                ShopCarTable.IC_URL to TEXT,
                ShopCarTable.PRICE to REAL
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(ShopCarTable.TABLE_NAME, true)//删除整张表
        onCreate(db)
    }

}