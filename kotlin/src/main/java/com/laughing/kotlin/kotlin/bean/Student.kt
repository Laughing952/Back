package com.laughing.kotlin.kotlin.bean

import android.util.Log

/**
 * 作者：Laughing on 2018/6/12 11:10
 * 邮箱：719240226@qq.com
 */
class Student(name: String, age: Int, var courseCount: Int) : Person() {

    //主构造器的方法（给父类属性赋值）
    init {
        this.name = name
        this.age = age
    }

    var height: Double = 1.75
        set(value) {    //field 指向的就是当前的this.height
            Log.e("TAG", "setter")
            field = value
        }

        get() {
            Log.e("TAG", "getter")

            return field
        }

    //声明常量
    companion object {
        val MATH: Int = 1
        val CHINESE: Int = 2
        val ENGLISH: Int = 3
    }


    constructor(name: String, age: Int, courseCount: Int, height: Double) : this(name, age, courseCount)

    fun play() {
        println("i am playing....")
    }

}