package com.laughing.kotlin.kotlin.inter

/**
 * 作者：Laughing on 2018/6/15 16:17
 * 邮箱：719240226@qq.com
 */

interface Person {
    //接口虽然可以定义属性，但是属性不能赋值
    var name: String

    fun eat()
    fun play()
}