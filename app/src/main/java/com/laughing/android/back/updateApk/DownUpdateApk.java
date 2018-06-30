package com.laughing.android.back.updateApk;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.laughing.android.back.Constant;
import com.laughing.android.back.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.view.View.INVISIBLE;

/**
 * Created by color on 16/5/16.Laughing use on 17-1-19
 */
public class DownUpdateApk extends Service {

    NotificationManager notificationManager;
    Notification myNotify;

    private String mUrl;//服务器端apk的路径
    private String apkName = Constant.APK_NAME;//apk的名字
    private RemoteViews rv;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:  //更改通知栏UI布局
                    int progress = Integer.parseInt(message.obj.toString());
                    rv = new RemoteViews(getPackageName(),
                            R.layout.my_notification);
                    rv.setTextViewText(R.id.text_title, "正在下载中...");
                    rv.setProgressBar(R.id.progress, 100, progress, false);
                    rv.setTextViewText(R.id.text_content, progress + "%");
                    myNotify.contentView = rv;
                    notificationManager.notify(0, myNotify);
                    break;
                case 2:
                    rv = new RemoteViews(getPackageName(),
                            R.layout.my_notification);
                    rv.setViewVisibility(R.id.text_content, INVISIBLE);//隐藏下载进度
                    rv.setViewVisibility(R.id.progress, INVISIBLE);//隐藏下载进度条
                    rv.setTextViewText(R.id.text_title, "下载完成，请点击安装");
                    //rv.setProgressBar(R.id.progress, 100, progress, false);//显示下载进度
                    //rv.setTextViewText(R.id.text_content, progress + "%");//显示下载进度条
                    myNotify.contentView = rv;

                    //下载完成,点击可以去安装文件
                    Intent intent = new Intent();
                    //把当前Activity从历史队列中独立出来，并清空其上的所有Activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction(Intent.ACTION_VIEW);// android.intent.action.VIEW
                    intent.setDataAndType(Uri.fromFile(new File(FileUtilForUpdateApk.getPath() + Constant.APK_NAME)),
                            "application/vnd.android.package-archive");
//                    myNotify.flags = Notification.FLAG_AUTO_CANCEL;//自动清除通知栏
                    myNotify.contentIntent = PendingIntent.getActivity(DownUpdateApk.this, 1, intent, 0);
                    stopSelf();//停止服务，但不立即执行
                    notificationManager.notify(0, myNotify);
                    break;
            }
            return false;
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        myNotify = new Notification();
        myNotify.icon = R.mipmap.ic_launcher;
        myNotify.tickerText = "准备下载...";
        myNotify.when = System.currentTimeMillis();

//        myNotify.flags = Notification.FLAG_NO_CLEAR;// 不能够自动清除

        rv = new RemoteViews(getPackageName(),
                R.layout.my_notification);
        rv.setProgressBar(R.id.progress, 100, 0, false);
        rv.setTextViewText(R.id.text_content, "开始下载"); //这里就是使用自定义布局了 初始化的时候不设置Intent,点击的时候就不会有反应了,亏得我还找了好久  T-T

        myNotify.contentView = rv;//通知加入布局
        notificationManager.notify(0, myNotify);
    }

    @Override//相当于onStart（），并取代onStart（）
    public int onStartCommand(Intent intent, int flags, int startId) {
        mUrl = intent.getExtras().getString("apkUrl");
        mUrl = "http://dl.360safe.com/mzm/360clockweather.apk";
        System.out.print("--------------->>" + mUrl);//http://dl.360safe.com/mzm/360clockweather.apk
        downLoad();
        return super.onStartCommand(intent, flags, startId);
    }

    private void downLoad() {
        OkHttpClient client = new OkHttpClient(); //使用okhttp下载文件
        Request request = new Request.Builder()
                .url(mUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    InputStream is = response.body().byteStream(); //成功的回调中拿到字节流
                    String path = Constant.APK_PATH;
                    long fileLength = response.body().contentLength(); //获取文件长度
                    FileUtilForUpdateApk.saveFile(is, path, Constant.APK_NAME, mHandler, fileLength); //保存下载的apk文件
                }
            }

        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUrl = null;
        myNotify = null;
        notificationManager = null;
    }

}
