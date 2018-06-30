package com.laughing.kotlin.test


/**
 * 作者：Laughing on 2018/6/6 17:17
 * 邮箱：719240226@qq.com
 */
class KotlinTest_DataType {

    var a: Int = 88_888
    var b: Int = 1


    //    @Test
    fun main() {
        print("这是主入口")
        compareData()
        transformTest()
        weiTest()
        weiTest2()
        booleanTest()
    }

    //比较数据类型
    fun compareData() {
        var data1: Int? = a   //？表示当前数值可以为null
        var data2: Int? = a   //？表示当前数值可以为null
        println(data1 == data2)     //表示值是否相等
        println(data1 === data2)    //表示对象的地址是否相等

    }

    //数据类型装换，需要在转换类型的前面加个toXXX
    fun transformTest() {
        var b: Double
        b = a.toDouble()
        println(b)

        var c = a.toFloat()
        println(c)

    }

    //位操作符
    fun weiTest() {
        if ((a == 888) or (b == 1)) {
            println("满足条件")
        } else {
            println("不满足条件")

        }
    }

    //位操作符
    fun weiTest2() {
        if ((a == 888) and (b == 1)) {
            println("满足条件")
        } else {
            println("不满足条件")

        }
    }

    fun booleanTest() {
        if (true) {
            println("是")
        } else {
            println("否")
        }

        if ((a == 888) || (b == 1)) {   //类似or
            println("满足条件")
        } else {
            println("不满足条件")

        }

        if ((a == 888) && (b == 1)) {  //类似and
            println("满足条件")
        } else {
            println("不满足条件")

        }
    }
}