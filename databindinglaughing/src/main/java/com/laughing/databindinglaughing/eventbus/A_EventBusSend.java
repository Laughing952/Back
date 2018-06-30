package com.laughing.databindinglaughing.eventbus;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.laughing.databindinglaughing.R;
import com.laughing.databindinglaughing.databinding.AEventBusSendBinding;
import com.laughing.databindinglaughing.eventbus.event.MessageEvent;
import com.laughing.databindinglaughing.eventbus.event.StickyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 作者：Laughing on 2018/6/11 11:21
 * 邮箱：719240226@qq.com
 */
public class A_EventBusSend extends AppCompatActivity {
    private AEventBusSendBinding binding;
    private boolean flag = true;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.a_event_bus_send);
        initListener();

    }

    //3.接收粘性事件
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void stickyEventBus(StickyEvent event) {
        binding.tvEventBusSendResult.setText(event.msg);
    }

    private void initListener() {
        binding.setClick(view -> {
            switch (view.getId()) {
                //主线程发送数据
                case R.id.bt_event_bus_send_main:
                    EventBus.getDefault().post(new MessageEvent("主线程发送过来的数据"));
                    finish();

                    break;
                //接收粘性数据
                case R.id.bt_event_bus_send_sticky:
                    //4.注册粘性事件
                    if (flag) {
                        EventBus.getDefault().register(this);
                        flag = false;
                    }
                    break;

                default:

                    break;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //5.取消注册
//        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();

    }
}
