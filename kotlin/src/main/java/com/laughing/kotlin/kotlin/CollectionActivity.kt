package com.laughing.kotlin.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.laughing.kotlin.R

class CollectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        collection()
    }

    private fun collection() {
        //可变与不可变集合    MutableList/List
        //List 的常见创建方式
        //1.创建不可变数组
        val letters = listOf("a", "b", "c")
        val emptyList = emptyList<String>()
        val mutableListOf = mutableListOf<String>("a", "b", "c")
        mutableListOf.add("d")
        for (letter in letters) {
            Log.e("TAG", letter)

        }
        letters.forEach { Log.e("TAG", "foreach---->$it") }
        letters.forEach { value -> Log.e("TAG", "value---->$value") }


        val mapOf = mapOf(
                Pair("key1", "value1"),
                Pair("key2", "value2"),
                Pair("key3", "value3"),
                Pair("key4", "value4")
        )
        val mutableMapOf = mutableMapOf(
                Pair("key1", "value1"),
                Pair("key2", "value2"),
                Pair("key3", "value3"),
                Pair("key4", "value4")
        )
        mutableMapOf.put("key5", "value5")
        mutableMapOf["key1"] = "new value 1"
        val mutableMapOf2 = mutableMapOf(
                "key1" to "value1",
                "key2" to "value2",
                "key3" to "value3",
                "key4" to "value4"
        )
        Log.e("TAG", "value---->${mutableMapOf["key1"]}")


        for (mutableEntry in mutableMapOf) {

            Log.e("TAG", "${mutableEntry.key} ----> ${mutableEntry.value}")
        }
        mutableMapOf.forEach { t, u -> Log.e("TAG", "$t ----> $u") }
    }
}
