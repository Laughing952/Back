package com.laughing.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.laughing.kotlin.R
import com.laughing.kotlin.adapter.Adapter_Cloth

/**
 * 作者：Laughing on 2018/6/7 11:26
 * 邮箱：719240226@qq.com
 */
class F_Cloth : Fragment() {
    private var rv_cloth: RecyclerView? = null
    private var adapter_cloth: Adapter_Cloth? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View.inflate(activity, R.layout.f_cloth, null)
        initViewFragment(view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataFragment()
        setListenerFragment()
        onClickFragment()
    }

    private fun initDataFragment() {
//        var con = Constants()
//        OkHttpUtils.post().url(con.GETDATA)
//                .addParams("selected", "1")
//                .build()
//                .execute(object : Callback<List<ClothBean>>() {
//                    override fun parseNetworkResponse(response: Response?, id: Int): List<ClothBean> {
//                        val string = response!!.body().string()
//                        val parseObject = JSON.parseObject(string)
//                        val result = parseObject.getString("result")
//                        return JSON.parseArray(result, ClothBean::class.java)
//
//
//                    }
//
//                    override fun onError(call: Call?, e: Exception?, id: Int) {
//
//                    }
//
//                    override fun onResponse(response: List<ClothBean>?, id: Int) {
//                        adapter_cloth = Adapter_Cloth(activity!!, response!!)   //如果非空 给它赋值 !!和 ?是对应的关系
//                        rv_cloth!!.adapter = adapter_cloth
//                    }
//                })

    }

    private fun initViewFragment(view: View) {
        val tv_cloth = view.findViewById<TextView>(R.id.tv_cloth)
        tv_cloth.setText("衣物列表")
        view.findViewById<RecyclerView>(R.id.rv_cloth)
        val gridLayoutManager = GridLayoutManager(activity, 2)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_cloth!!.layoutManager = gridLayoutManager
        rv_cloth!!.setHasFixedSize(true)

    }

    private fun onClickFragment() {

    }

    private fun setListenerFragment() {

    }
}