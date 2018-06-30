package com.laughing.kotlin.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.laughing.kotlin.R
import com.laughing.kotlin.kotlin.inter.Person
import com.laughing.kotlin.kotlin.inter.class_authorize.Student2
import com.laughing.kotlin.kotlin.test.Product

class InnerClassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inner_class)


        var product = Product()
        product.price = -18.0
//        Log.e("TAG", "laughing----${product.price} ------------------->")
        product.pointPrice = -18.0
//        Log.e("TAG", "laughing----${product.pointPrice} ------------------->")

//        product.price2 = -18.0
//        Log.e("TAG", "laughing  ${product.price2} ------------------->")
//
//        product.price2 = 10.0
//        Log.e("TAG", "laughing   ${product.price2} ------------------->")

//
//        product.totalPrice = 10.0
//        Log.e("TAG", "laughing   ${product.totalPrice} ------------------->")
//        product.totalPrice = -18.0
//        Log.e("TAG", "laughing  ${product.totalPrice} ------------------->")


//        product.name2 = "sssssss"
//        Log.e("TAG", "laughing  ${product.name2} ------------------->")

//        类的委托
        var stu2=Student2(object :Person{
            override var name: String
                get() = "zhang san"
                set(value) {}

            override fun eat(){
                 Log.e("TAG","$name -------eating---------------->")
            }

            override fun play() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        stu2.eat()


        ///////////////////////////InnerClass//////////////////////////////
//        button2.setOnClickListener {
//            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
//
//        }
//        Thread {
//            Log.e("TAG", Thread.currentThread().name)
//        }.start()
//        thread {
//            Log.e("TAG", Thread.currentThread().name + "  simple")
//            var result = URL("http://mall.520it.com/login").readText()
//            Log.e("TAG", "laughing----------------------->$result")
//
//        }
        ///////////////////////////InnerClass//////////////////////////////

    }
}
