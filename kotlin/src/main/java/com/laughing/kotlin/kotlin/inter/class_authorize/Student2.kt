package com.laughing.kotlin.kotlin.inter.class_authorize

import com.laughing.kotlin.kotlin.inter.Person

/**
 * 类的委托
 * 作者：Laughing on 2018/6/15 16:36
 * 邮箱：719240226@qq.com
 */
class Student2(var p: Person) : Person by p {

}