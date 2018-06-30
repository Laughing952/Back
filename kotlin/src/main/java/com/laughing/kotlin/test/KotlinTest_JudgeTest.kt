package com.laughing.kotlin.test


/**
 * Kotlin 中的条件判断
 * 作者：Laughing on 2018/6/6 17:17
 * 邮箱：719240226@qq.com
 */
class KotlinTest_JudgeTest {


    //    @Test
    fun main() {
        print("这是主入口")
        //if 表达式
        var data1 = 1
        var data2 = 3
        var data3 = data1
        if (data1 > data2)
            data3 = data2
        print(data3)

        if (data1 > data2) {
            data3 = data2
        } else {
            data3 = data1
        }
        print(data3)

        //作为表达式
        data3 = if (data1 < data2) data1 else data2
        print(data3)

        //if表达式 给一个变量赋值
        data3 = if (data1 < data2) {
            data1
        } else {
            data2
        }
        print(data3)

        //when
        when (data1) {
            1 -> print("满足条件1")
            2 -> print("满足条件2")
            3 -> print("满足条件3")
            4 -> print("满足条件4")
            in 0..10 -> print("在此区间内")
            else -> print("都不满足")
        }
    }


}