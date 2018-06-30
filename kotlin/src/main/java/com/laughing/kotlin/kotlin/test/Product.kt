package com.laughing.kotlin.kotlin.test

import android.util.Log
import kotlin.properties.Delegates

/**
 * 作者：Laughing on 2018/6/15 10:05
 * 邮箱：719240226@qq.com
 */
class Product {
    var name: String = ""
    var price: Double by Delegate()
    var pointPrice: Double by Delegate()

    //Observable检查传入数据
    var price2: Double by Delegates.observable(0.0) { property, old, newValue ->
        if (newValue < 0) Log.e("TAG", "newValue lt 0")
        else Log.e("TAG", "newValue gt 0")
    }

    //vetoable决定是否保留某个数据
    var totalPrice: Double by Delegates.vetoable(3.0) { property, old, newValue ->
        //只有大于等于0才能被保留
        newValue >= 0.0
    }

    //这个值我会自己把控，系统不需要处理
    var name2: String by Delegates.notNull() //Property name2 should be initialized before get.

    //该构造器内部的是怎么写的呢？
//    public fun <T: Any> notNull(): ReadWriteProperty<Any?, T> = NotNullVar()
//
//    private class NotNullVar<T: Any>() : ReadWriteProperty<Any?, T> {
//        private var value: T? = null
//        //这里已经说明了 如果你一开始没给该值赋新的数据，就直接调用他的get() 则会抛出一个IllegalStateException
//        public override fun getValue(thisRef: Any?, property: KProperty<*>): T {
//            return value ?: throw IllegalStateException("Property ${property.name} should be initialized before get.")
//        }
//
//        public override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
//            this.value = value
//        }
//    }


}