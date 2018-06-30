package com.laughing.kotlin.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.laughing.kotlin.R
import com.laughing.kotlin.bean.ClothBean

/**
 * 作者：Laughing on 2018/6/7 15:57
 * 邮箱：719240226@qq.com
 */
class Adapter_Cloth(var context: Context, list: List<ClothBean>) : BaseQuickAdapter<ClothBean, BaseViewHolder>(R.layout.item_cloth, list) {
    override fun convert(helper: BaseViewHolder?, item: ClothBean?) {
        helper!!.setText(R.id.cloth_name, item!!.goods_name)
                .setText(R.id.cloth_pr, item!!.vip_price)

        val imageView = helper.getView<ImageView>(R.id.cloth_ph)
        Glide.with(context).load(item.original_img).into(imageView)

    }
}