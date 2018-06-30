package com.laughing.android.countdownlauhging;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_djs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_djs = (Button) findViewById(R.id.btn_djs);
        //new倒计时对象,总共的时间,每隔多少秒更新一次时间
        final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000,1000);

        //给Button设置点击时间,触发倒计时
        btn_djs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCountDownTimer.start();
            }
        });
    }

    //复写倒计时
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            btn_djs.setClickable(false);
            btn_djs.setText(l/1000+"s");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            btn_djs.setText("重新获取验证码");
            //设置可点击
            btn_djs.setClickable(true);
        }
    }
}
