package com.laughing.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 衣物的
 * 作者：Laughing on 2018/6/7 11:26
 * 邮箱：719240226@qq.com
 */
class F_Mine : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initDataFragment()
        setListenerFragment()
        onClickFragment()
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    private fun onClickFragment() {

    }

    private fun setListenerFragment() {

    }

    private fun initDataFragment() {

    }
}