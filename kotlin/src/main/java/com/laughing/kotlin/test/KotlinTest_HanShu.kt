package com.laughing.kotlin.test


/**
 * 作者：Laughing on 2018/6/6 17:17
 * 邮箱：719240226@qq.com
 */
class KotlinTest_HanShu {

    var a = 1
    var b = 2
    var c = "dog"
    var d = "wangcai is $c"
    var f = "${d.replace("wangcai", "xiaohei")}"


//    @Test
    fun main() {
        print("这是主入口")
        println(add(a, b))
        println(add(3, 4))
        println(add2(5, 6))
        add3(7, 8)
        println(d)
        println(f)

    }

    //返回时需要声明返回参数的类型
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    //自动判断返回类型
    fun add2(a: Int, b: Int) = a + b

    //无返回参数类型，类似android中的void
    fun add3(a: Int, b: Int) {
        print(a + b)
    }

    //无返回参数类型，类似android中的void
    fun add4(a: Int, b: Int): Unit {
        print(a + b)
    }

}