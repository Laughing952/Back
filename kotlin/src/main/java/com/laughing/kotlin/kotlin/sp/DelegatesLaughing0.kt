package com.laughing.kotlin.kotlin.sp

import android.content.Context

/**
 * 作者：Laughing on 2018/6/16 11:13
 * 邮箱：719240226@qq.com
 */
object DelegatesLaughing0 {
    fun preference(context: Context, name: String, defValue: Long) = PreferenceDelegate(context, name, defValue)

}