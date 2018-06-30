package com.laughing.kotlin.anko.bean

/**
 * 使用了data 关键字，系统会默认给该对象的属性创建getter/setter，并且还会自动生成toString方法
 * 作者：Laughing on 2018/6/15 17:12
 * 邮箱：719240226@qq.com
 */
data class ShopCar(var id: Long,
                   var name: String,
                   var iconUrl: String,
                   var price: Double)