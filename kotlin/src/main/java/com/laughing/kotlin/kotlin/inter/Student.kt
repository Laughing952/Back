package com.laughing.kotlin.kotlin.inter

import android.util.Log

/**
 * 作者：Laughing on 2018/6/15 16:19
 * 邮箱：719240226@qq.com
 */
class Student(override var name: String) : Person {
    override fun eat() {
        Log.e("TAG", "$name ---------eat------------->")
    }

    override fun play() {
        Log.e("TAG", "$name  ----------play------------->")

    }

    fun study() {
        Log.e("TAG", "$name  ----------study------------->")

    }
}