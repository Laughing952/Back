package com.laughing.kotlin.kotlin.test

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * 作者：Laughing on 2018/6/15 10:36
 * 邮箱：719240226@qq.com
 */
class DBOpenHelper(mContext: Context) : SQLiteOpenHelper(mContext, DB_NAME, null, DB_VERSION) {

    init {
        Log.e("TAG","laughing---------DBOpenHelper called------------->")
    }
    companion object {
        const val DB_NAME = "xxx.db"
        const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}