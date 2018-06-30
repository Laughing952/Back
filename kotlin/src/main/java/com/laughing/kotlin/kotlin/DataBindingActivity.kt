package com.laughing.kotlin.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.laughing.kotlin.R
import kotlinx.android.synthetic.main.activity_data_binding.*


/**
 * 作者：Laughing on 2018/6/14 09:57
 * 邮箱：719240226@qq.com
 */
class DataBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding)
        button.text="kotlin is very nice!"
        checkBox.isChecked = true


    }

}