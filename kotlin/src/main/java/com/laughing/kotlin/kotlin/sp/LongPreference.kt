package com.laughing.kotlin.kotlin.sp

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * long 类型数据的存储与读取
 * 作者：Laughing on 2018/6/16 10:56
 * 邮箱：719240226@qq.com
 */
class LongPreference(val mContext: Context, val name: String, val defValue: Long) : ReadWriteProperty<Any?, Long> {
    //lazy 要用 val来声明
    val mSp by lazy {
        mContext.getSharedPreferences("laughingFiles", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Long {

        return mSp.getLong(name, defValue)

    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        mSp.edit().putLong(name, value).apply()

    }

}