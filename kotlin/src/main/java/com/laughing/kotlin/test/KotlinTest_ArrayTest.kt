package com.laughing.kotlin.test


/**
 * 作者：Laughing on 2018/6/6 17:17
 * 邮箱：719240226@qq.com
 */
class KotlinTest_ArrayTest {


    //    @Test
    fun main() {
        print("这是主入口")
        arrayTest()
        inTest()

    }

    //比较数据类型
    fun arrayTest() {
        var a = arrayOf(1, 2, 3, 4, 5)
        println(a[0])

        var b = arrayOf("xiaoming", "xiaoli", 3)

        println(b[0])
        var c = intArrayOf(1, 2, 3, 4, 5)   //只能定义Int类型
        println(c[0])

    }

    fun inTest() {
        for (i in 1..5) {   //表示大于等于最小数，小于等于最大数
            println(i)      //执行结果 1,2,3，4,5

        }
//        for (i in 5..1) {   //kotlin中不支持从大到小，只支持从小到大
//            println(i)      //执行结果什么也没有
//
//        }

        for (i in 1..10 step 2)     //step表示每次递增的数值
            println(i)

        for (i in 1 until 10)     //until表示大于等于最小数，小于最大数
            println(i)


    }


}