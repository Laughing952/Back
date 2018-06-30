package com.laughing.android.back;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.laughing.android.back.updateApp.PackUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext = this;
    private String mLocalVersionName;//获取本地版本名
    private int mLocalVersionNum;//获取本地版本号
    String mServerVersionName;
    int mServerVersionNum;
    private Button mBt_photo;
    private TextView mTvname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();//隐藏标题栏
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_main);
        mTvname = (TextView) findViewById(R.id.tv_name);
        mBt_photo = (Button) findViewById(R.id.bt_photo);
        mTvname.setOnClickListener(this);
        mBt_photo.setOnClickListener(this);

        /*tvname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "你好", Toast.LENGTH_SHORT).show();

            }
        });*/
        //因为分享授权中需要使用一些对应的权限，如果你的targetSdkVersion设置的是23或更高，需要提前获取权限。（如果targetSdkVersion是22或以下，可以忽略该问题）
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                mLocalVersionName = new PackUtils(mContext).getVersionName();

                mLocalVersionNum = new PackUtils(mContext).getVersionCode();
                mServerVersionName = "2";//服务器版本名
                mServerVersionNum = 2;//服务器版本号
                Toast.makeText(getApplicationContext(), "请稍后......", Toast.LENGTH_SHORT).show();
                if (new PackUtils(mContext).isUpgradeVersion(mLocalVersionNum, mServerVersionNum)) {

                    showNoticeDialog(Constant.APK_PATH + Constant.APK_NAME);
//                    showNoticeDialog(Constant.APK_PATH);
                }
                break;
            case R.id.bt_photo:
                startActivity(new Intent(mContext, SimpleActivity.class));
                break;
            default:

                break;
        }
    }


    /**
     * 显示软件更新对话框
     */
    public void showNoticeDialog(final String apkUrl) {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("当前版本" + mLocalVersionName + ",新版本" + mServerVersionName + ",是否更新?");
        // 更新
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                new UpdateAppManager
//                        .Builder()
//                        //当前Activity
//                        .setActivity(MainActivity.this)
//                        //更新地址
//                        .setUpdateUrl("http://dl.360safe.com/mzm/360clockweather.apk")
//                        //实现httpManager接口的对象
//                        .setHttpManager(new UpdateAppHttpUtil())
//                        .build()
//                        .update();
//                Intent serviceIntent = new Intent(mContext, DownUpdateApk.class);
//                serviceIntent.putExtra("apkUrl", apkUrl);
//                startService(serviceIntent);

            }
        });

        // 稍后更新
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                //版本号第二位不同意味着大版本升级，点击取消按钮强制退出
//                if (mLocalVersionName.charAt(2) != (mServiceVersion.charAt(2))) {
//                    finish();
//                }
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

    }


}
