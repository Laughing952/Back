package com.laughing.kotlin.kotlin.test

import android.util.Log
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 作者：Laughing on 2018/6/15 09:57
 * 邮箱：719240226@qq.com
 */
class Delegate0<T> : ReadWriteProperty<Any?, T> {
    var mValue: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        Log.e("TAG", "laughing1----$thisRef------------$property----------->")
        return mValue!!
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        Log.e("TAG", "laughing2----$thisRef-------$property--------$value---->")
        this.mValue = value
    }
}