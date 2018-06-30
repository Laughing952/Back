package com.laughing.kotlin.test


/**
 * 作者：Laughing on 2018/6/6 17:17
 * 邮箱：719240226@qq.com
 */
class KotlinTest_Generic {


    //    @Test
    fun main() {
        print("这是主入口")
        var a: Int = 1
        var b = Base<Int>(2)
        println(b.value)
        var c = Base<String>("c de zhi")
        println(c.value)

        val d = Base("xiao hong ")  //自动判断返回参数的类型
        println(d.value)
    }

    class Base<T>(t: T) {
        var value = t
    }

}