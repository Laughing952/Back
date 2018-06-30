package com.laughing.kotlin.anko.dao

import android.content.Context
import com.laughing.kotlin.anko.bean.ShopCar
import com.laughing.kotlin.anko.constant.ShopCarTable
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * 作者：Laughing on 2018/6/15 17:47
 * 邮箱：719240226@qq.com
 */
class ShopCarDao(var ctx: Context) {
    val mDBOpenHelper by lazy {
        DBOpenHelper(ctx)
    }

    fun saveShopCars(datas: List<ShopCar>) {
        //保存数据的安全性(事务)
        mDBOpenHelper.use {
            //增删改时用
            val db = mDBOpenHelper.writableDatabase

            datas.forEach {
                //写法1：
//                val map = mutableMapOf<String, Any>()
//                map[ShopCarTable._ID] = it.id
//                map[ShopCarTable.NAME] = it.name
//                map[ShopCarTable.IC_URL] = it.iconUrl
//                map[ShopCarTable.PRICE] = it.price
//                //将key-value作为一个个的键值对
//                val list = map.map {
//                    Pair(it.key, it.value)
//                }
//                //将list装换成Array
//                val typedArray = list.toTypedArray()
//                // vararg values: Pair<String, Any?>
//                //*typedArray 将数组转变为可变参数
//                db.insert(ShopCarTable.TABLE_NAME, *typedArray)

                //写法2：
                val map = mutableMapOf<String, Any>()
                with(it) {
                    map[ShopCarTable._ID] = id
                    map[ShopCarTable.NAME] = name
                    map[ShopCarTable.IC_URL] = iconUrl
                    map[ShopCarTable.PRICE] = price
                }
                //将key-value作为一个个的键值对
                val list = map.map {
                    Pair(it.key, it.value)
                }
                //将list装换成Array
                val typedArray = list.toTypedArray()
                // vararg values: Pair<String, Any?>
                //*typedArray 将数组转变为可变参数
                db.insert(ShopCarTable.TABLE_NAME, *typedArray)

            }

        }
    }

    fun queryShopCars(): List<ShopCar>? {
        var result: List<ShopCar>? = null
        mDBOpenHelper.use {
            val db = mDBOpenHelper.readableDatabase
            //查询的要素：表名  字段  查询条件
            val select = select(ShopCarTable.TABLE_NAME)
            //拿到了select的结果集（表） 转换为  对象列表
            result = select.parseList(object : MapRowParser<ShopCar> {
                //把每一个item的表数据  转换成对象
                override fun parseRow(columns: Map<String, Any?>): ShopCar {
                    val id = columns[ShopCarTable._ID] as Long
                    val name = columns[ShopCarTable.NAME] as String
                    val iconUrl = columns[ShopCarTable.IC_URL] as String
                    val price = columns[ShopCarTable.PRICE] as Double
                    return ShopCar(id, name, iconUrl, price)

                }

            })


        }
        return result
    }
}