package com.laughing.kotlin.kotlin.test

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 作者：Laughing on 2018/6/15 09:57
 * 邮箱：719240226@qq.com
 */
class Delegate : ReadWriteProperty<Any?, kotlin.Double> {
    var mValue: kotlin.Double? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): kotlin.Double {
//        Log.e("TAG", "laughing1----$thisRef------------$property----------->")
        return mValue!!
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: kotlin.Double) {
//        Log.e("TAG", "laughing2----$thisRef-------$property--------$value---->")
        if (value >= 0) this.mValue = value else this.mValue = 0.0
    }
}