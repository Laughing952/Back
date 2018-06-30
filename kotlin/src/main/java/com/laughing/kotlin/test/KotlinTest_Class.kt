package com.laughing.kotlin.test


/**
 * 作者：Laughing on 2018/6/6 17:17
 * 邮箱：719240226@qq.com
 */
class KotlinTest_Class {


    //    @Test
    fun main() {
        print("这是主入口")
        var p = Person()    //不需要new 关键字
        println(p.name)
        println(p.age)
        p.age = 20
        println(p.age)

    }

    class Person {
        var name: String = "xiaoming"
        var age: Int = 15
            get() = field
            set(value) {    //field关键字是用在类的属性上，它当前的属性，及当前属性的值
                if (age < 20) {
                    field = value
                } else {
                    field = 18
                }
            }

        init {  //主构造器（最先执行）
            println("dajiahao woshi$name")
        }

        constructor() {
            println("dajiahao wojinnian$age sui")

        }

    }

}