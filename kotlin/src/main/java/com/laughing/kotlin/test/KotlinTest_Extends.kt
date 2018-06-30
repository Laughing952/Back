package com.laughing.kotlin.test


/**
 * 作者：Laughing on 2018/6/6 17:17
 * 邮箱：719240226@qq.com
 */
class KotlinTest_Extends {


    //    @Test
    fun main() {
        print("这是主入口")
        val worker = Worker("xiaozhang", 20)
        val worker2 = Worker2("xiaozhang", 20, 1)
        println("nianlingshi ${worker.age}")
        println("gonghaoshi ${worker2.num}")
        Dog("benben", 3)
        Dog2("benben", 3, 20)
        worker.like()

    }

    /////////////////////////// 基类有主构造函数///////////////////////////////
    //open表示当前类可以被其他类继承
    open class Person(var name: String, var age: Int) {
        open fun like() {
            println("aihaoshi ")
        }

    }

    //子类有主构造函数，则必须要在主构造函数中进行初始化基类的属性
    class Worker(name: String, age: Int) : Person(name, age) {
        override fun like() {
            //super.like()
            println("aihaoshi zileide fangfa ")

        }
    }

    class Worker2(name: String, age: Int, var num: Int) : Person(name, age) {

    }

    /////////////////////////// 基类无主构造函数///////////////////////////////
    open class Anim(name: String) {
        constructor(name: String, age: Int) : this(name) {
            println("基类的次构造方法")

        }


    }

    class Dog : Anim {
        constructor(name: String, age: Int) : super(name, age) {
            println("子类的次构造方法")
            println(name)
            println(age)


        }


    }

    class Dog2 : Anim {
        constructor(name: String, age: Int, weight: Int) : super(name, age) {
            println("子类的次构造方法")
            println(name)
            println(age)
            println("体重是：$weight")


        }


    }

}