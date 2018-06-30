package com.laughing.kotlin.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.laughing.kotlin.R
import com.laughing.kotlin.kotlin.bean.Person
import com.laughing.kotlin.kotlin.bean.Student
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        val NAME_KEY = "MainActivity::key"
        val AGE_KEY = "MainActivity::value"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
        getIntentVaule()
        initListener()
    }

    /**
     * 获取intent中从上一个页面传过来的值
     */
    private fun getIntentVaule() {
        val name = intent.getStringExtra(NAME_KEY)
        val age = intent.getIntExtra(AGE_KEY, 0)
        Log.e("TAG", "$name  $age")

    }

    private fun initView() {
        tv_name.setText("Laughing")
        var p = Person("zhang san", 18)
        var s = Student("zhang san", 10, courseCount = 3)
        var s1 = Student("zhang san", 18, 3)
        var s2 = Student("zhang san", 18, 3, 4.0)
        Log.e("TAG", "${s.name}")
        Log.e("TAG", "${s.height}")
        Log.e("TAG", s.name + "  " + s.age + "  " + s.height)

//        var s3: Student2? = null
//        s3?.play()

        var s3 = Student("li si", 10, 3)
        s3.height = 1.50
        Log.e("TAG", "${s3.height} ------------------------>")
        var height = s3.height
        Log.e("TAG", "$height ----------------height-------->")


        val linearLayout = findViewById<LinearLayout>(R.id.parent)
        val imageView = linearLayout.get(2) as ImageView
        imageView.setImageResource(R.mipmap.ic_launcher)
    }


    private fun initData() {
        //Kotlin扩展类用法
//        var vg: ViewGroup? = null
//        var childAt = vg?.get(3)


    }

    fun ViewGroup.get(index: Int) = this.getChildAt(index)

    private fun initListener() {
        tv_name.setOnClickListener { v ->
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
            println("hello")

        }
    }
}


