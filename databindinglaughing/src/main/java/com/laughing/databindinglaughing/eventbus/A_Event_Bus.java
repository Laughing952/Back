package com.laughing.databindinglaughing.eventbus;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.laughing.databindinglaughing.R;
import com.laughing.databindinglaughing.databinding.AEventBusBinding;
import com.laughing.databindinglaughing.eventbus.event.MessageEvent;
import com.laughing.databindinglaughing.eventbus.event.StickyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * eventBus
 */
public class A_Event_Bus extends AppCompatActivity {
    private AEventBusBinding binding;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.a_event_bus);
        initData();
        initListener();

    }

    private void initData() {
        //1.注册广播
        EventBus.getDefault().register(this);

    }

    private void initListener() {
        binding.setClick(view -> {
            switch (view.getId()) {
                case R.id.bt_event_bus_send:
                    startActivity(new Intent(this, A_EventBusSend.class));

                    break;
                case R.id.bt_event_bus_sticky:
                    //2.发送黏性事件
                    EventBus.getDefault().postSticky(new StickyEvent("我是黏性事件"));
                    startActivity(new Intent(this, A_EventBusSend.class));

                    break;

                default:

                    break;
            }
        });

    }

    //5.接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(MessageEvent event) {

        //显示接收到的消息
        binding.tvEventBusResult.setText(event.name);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //2.解注册
        EventBus.getDefault().unregister(this);

    }
}
