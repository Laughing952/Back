package com.laughing.kotlin.kotlin.test

import android.app.Application

/**
 * 作者：Laughing on 2018/6/15 10:41
 * 邮箱：719240226@qq.com
 */
class MyApplication : Application() {
    //lazy：懒加载当mDBOpenHelper被调用的时候 才去创建
    val mDBOpenHelper: DBOpenHelper by lazy {
        DBOpenHelper(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        val db = mDBOpenHelper.readableDatabase
    }
}