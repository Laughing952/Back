package com.laughing.kotlin.test


/**
 * 作者：Laughing on 2018/6/6 17:17
 * 邮箱：719240226@qq.com
 */
class KotlinTest_Class2 {


    //    @Test
    fun main() {
        print("这是主入口")
        var num = a.b().getNum()
        println(num)
        c().d().kaiPao()


    }

    //类的嵌套是如何调用的
    class a {
        class b {
            fun getNum() = 10
        }

    }

    class c {
        var str: String = "kaiPaoA"

        inner class d {      //内部类可以直接引用外部类的属性
            fun kaiPao() {
                println("liYunLong ni" + str)

            }
        }

    }

}