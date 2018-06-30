package com.laughing.android.back;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import java.util.ArrayList;

/**
 * 作者：Laughing on 2018/3/30 17:04 use takePhoto from github
 * 邮箱：719240226@qq.com
 */

public class SimpleActivity extends TakePhotoActivity {

    private CustomHelper customHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView= LayoutInflater.from(this).inflate(R.layout.common_layout,null);
        setContentView(contentView);
        customHelper=CustomHelper.of(contentView);
    }

    public void onClick(View view) {
        customHelper.onClick(view,getTakePhoto());
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        showImg(result.getImages());
    }

    private void showImg(ArrayList<TImage> images) {
        Intent intent=new Intent(this,ResultActivity.class);
        intent.putExtra("images",images);
        startActivity(intent);
    }
}

