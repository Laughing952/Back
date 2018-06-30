package com.laughing.kotlin.kotlin.type

/**
 * 泛型
 * in：传入参数
 * out：返回值
 * 作者：Laughing on 2018/6/16 10:24
 * 邮箱：719240226@qq.com
 */
class TypeClass {
//class TypeClass<T, K> {
    //class TypeClass<in T, out K> {

    //如果再函数中创建泛型应该是如下写法
    fun <T, K> doSomething0(params: T): K? {


        return params.toString() as K
    }
//    fun doSomething(params: T): K? {
//        if (params is Double) return "您支付了 $params yuan" as K
//
//        return null
//    }

}