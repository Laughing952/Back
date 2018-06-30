package com.laughing.kotlin.test


/**
 * 作者：Laughing on 2018/6/6 17:17
 * 邮箱：719240226@qq.com
 */
class KotlinTest_Interface {


    //    @Test
    fun main() {
        print("这是主入口")
        var s = Student(20)
        s.getName()
        s.getAge()
        var s2 = Student2()
        println(s2.weight)
    }

    //interface表示当前类可以被实现
    interface Person {
        var weight: Int
        fun getName()
        fun getAge()

    }

    class Student(override var weight: Int) : Person {
        override fun getAge() {
            println("mingzishi")
        }

        override fun getName() {
            println("nianlingshi")

        }

    }

    class Student2 : Person {
        override var weight: Int = 20
        override fun getAge() {
            println("mingzishi")
        }

        override fun getName() {
            println("nianlingshi")

        }

    }
}