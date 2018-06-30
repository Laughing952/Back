package com.laughing.kotlin.test

/**
 * 作者：Laughing on 2018/6/6 17:17
 * 邮箱：719240226@qq.com
 */
class KotlinTest_Circulation {


    //        @Test
    fun main() {
        print("这是主入口")
        forTest()
        whileTest()
        breakTest()
    }


    fun forTest() {
        val items = listOf<String>("xiaoming", "huangzhogn", "luban")
        for (item in items) {
            println(item)
        }
        for (index in items.indices) {
            println("index is $index is ${items[index]}")

        }


    }

    fun whileTest() {
        var a = 5
        //只要满足条件就执行
        while (a > 0) {
            println(a--)

        }
        //do...while循环至少执行一次
        var b = 10
        do {
            println(b)
        } while (b > 10)
    }

    fun breakTest() {
        for (i in 1..5) {
            if (1 == 2)
                continue    //跳过当次循环，进入下一次循环
            if (1 == 2) break   //返回当前for循环
            if (1 == 2) return   //返回当前包含它的最大方法，当前方法后面的不再执行
            println(i)
        }
        println("执行")
    }

}