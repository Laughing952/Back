package com.laughing.android.back;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TImage;

import java.io.File;
import java.util.ArrayList;


/**
 * - 支持通过相机拍照获取图片
 * - 支持从相册选择图片
 * - 支持从文件选择图片
 * - 支持多图选择
 * - 支持批量图片裁切
 * - 支持批量图片压缩
 * - 支持对图片进行压缩
 * - 支持对图片进行裁剪
 * - 支持对裁剪及压缩参数自定义
 * - 提供自带裁剪工具(可选)
 * - 支持智能选取及裁剪异常处理
 * - 支持因拍照Activity被回收后的自动恢复
 * Author: crazycodeboy
 * Date: 2016/9/21 0007 20:10
 * Version:4.0.0
 * 技术博文：http://www.cboy.me
 * GitHub:https://github.com/crazycodeboy
 * Eamil:crazycodeboy@gmail.com
 */
public class ResultActivity extends Activity {
    private Context mContext = this;
    ArrayList<TImage> images;
    private Button mBt;
    private File mFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_layout);
        images = (ArrayList<TImage>) getIntent().getSerializableExtra("images");
        showImg();
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "开始上传比对，请稍后....", Toast.LENGTH_SHORT).show();
                uploadFile(mFile);
            }
        });
    }

    /**
     * 显示图片的方法（对takePhoto中原有方法及布局尽行了修改）
     */
    private void showImg() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llImages);

//        for (int i = 0, j = images.size(); i < j - 1; i += 2) {
        View view = LayoutInflater.from(this).inflate(R.layout.image_show, null);
        mBt = (Button) view.findViewById(R.id.bt);
        ImageView imageView1 = (ImageView) view.findViewById(R.id.imgShow1);


//            ImageView imageView2 = (ImageView) view.findViewById(R.id.imgShow2);
        mFile = new File(images.get(0).getCompressPath());
        Glide.with(this).load(mFile).into(imageView1);
//            Glide.with(this).load(new File(images.get(i).getCompressPath())).into(imageView1);
//            Glide.with(this).load(new File(images.get(i + 1).getCompressPath())).into(imageView2);
        linearLayout.addView(view);
//        }
//        if (images.size() % 2 == 1) {
//            View view = LayoutInflater.from(this).inflate(R.layout.image_show, null);
//            ImageView imageView1 = (ImageView) view.findViewById(R.id.imgShow1);
//            Glide.with(this).load(new File(images.get(images.size() - 1).getCompressPath())).into(imageView1);
//            linearLayout.addView(view);
//        }

    }

    /**
     * 上传文件的业务逻辑
     *
     * @param file 图片
     */
    private void uploadFile(File file) {

    }
}
