package com.laughing.kotlin.kotlin.sp

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 作者：Laughing on 2018/6/16 10:56
 * 邮箱：719240226@qq.com
 */
class PreferenceDelegate<T>(val mContext: Context, val name: String, val defValue: T)
    : ReadWriteProperty<Any?, T> {

    //lazy 要用 val来声明
    val mSp by lazy {
        mContext.getSharedPreferences("laughingFiles", Context.MODE_PRIVATE)
    }

    /**
     * 获取值
     */
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        with(mSp) {
            var result = when (defValue) {
                is kotlin.Long -> getLong(name, defValue)
                is kotlin.Int -> getInt(name, defValue)
                is kotlin.String -> getString(name, defValue)
                is kotlin.Float -> getFloat(name, defValue)
                is kotlin.Boolean -> getBoolean(name, defValue)
                else -> throw IllegalArgumentException("传入SharePreference中的类型有误，请检查defValue")
            }
            return result as T
        }


    }

    /**
     * 设置值
     */
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
//        mSp.edit().putLong(name, value).apply()
        val editor = mSp.edit()
        with(editor) {
            when (value) {
                is kotlin.Long -> putLong(name, value).apply()
                is kotlin.Int -> putInt(name, value).apply()
                is kotlin.String -> putString(name, value).apply()
                is kotlin.Float -> putFloat(name, value).apply()
                is kotlin.Boolean -> putBoolean(name, value).apply()
                else -> throw IllegalArgumentException("传入SharePreference中的类型有误，请检查defValue")
            }
        }
    }

}