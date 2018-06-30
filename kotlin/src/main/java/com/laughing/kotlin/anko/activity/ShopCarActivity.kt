package com.laughing.kotlin.anko.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.laughing.kotlin.R
import com.laughing.kotlin.kotlin.MainActivity
import com.laughing.kotlin.kotlin.sp.DelegatesLaughing
import org.jetbrains.anko.startActivity

class ShopCarActivity : AppCompatActivity() {
    //    var testValue by LongPreference(this,"testValue")
    var testValue by DelegatesLaughing.preference<Long>(this, "testValue", 0L)
    var name by DelegatesLaughing.preference<String>(this, "name", "")
    var age by DelegatesLaughing.preference<Int>(this, "age", 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_car)

        ///////////////////////anko操作数据库////////////////////////
//        var shopCarDao = ShopCarDao(this)
//
//        var list = mutableListOf<ShopCar>()
//        list.add(ShopCar(11, "qiao ke li", "/img/1.jpg", 18.50))
//        list.add(ShopCar(21, "qiao ke li2", "/img/2.jpg", 18.52))
//        list.add(ShopCar(31, "qiao ke li3", "/img/3.jpg", 18.53))
//        list.add(ShopCar(41, "qiao ke li4", "/img/4.jpg", 18.54))
//
//        shopCarDao.saveShopCars(list)
//        val shopCars = shopCarDao.queryShopCars()
//        shopCars?.forEachIndexed { index, shopCar ->
//            Log.e("TAG", "laughing----------------------->" + shopCar.toString())
//        }

        //////////////////////验证泛型的使用////////////////////////


//        val typeClass = TypeClass()
//        val doSomething0 = typeClass.doSomething0<Double, String>(18.0)
//        Log.e("TAG", "laughing----------$doSomething0------------->")

//        val typeClass = TypeClass<Double, String>()
//        val doSomething = typeClass.doSomething(18.0)
//        Log.e("TAG","laughing----------$doSomething------------->")

        //////////////////////保存到SP中/////////////////////////////
//        testValue = 20L
//        Log.e("TAG", "laughing-------  $testValue  ---------------->")
//        name = "zhang san"
//        Log.e("TAG", "laughing-------  $name  ---------------->")
//        age = 25
//        Log.e("TAG", "laughing-------  $age  ---------------->")

        //////////////////////使用枚举/////////////////////////////
//        var day = Day.MONDAY
//        var direction = Direction.EAST
//        //获取枚举类中的值
//        Log.e("TAG", "laughing---------${direction.value}-------------->")
//        //获取枚举类中的值的个数
//        Log.e("TAG", "laughing---------${Direction.values().size}-------------->")
//        //获取枚举类中的索引
//        Log.e("TAG", "laughing---------${direction.ordinal}-------------->")
//        Log.e("TAG", "laughing---------${direction.name}-------------->")

        //////////////////////anko intent使用/////////////////////////////

//        startActivity<MainActivity>()
        startActivity<MainActivity>(
                MainActivity.NAME_KEY to "laughing",
                MainActivity.AGE_KEY to 25
        )
    }
}
