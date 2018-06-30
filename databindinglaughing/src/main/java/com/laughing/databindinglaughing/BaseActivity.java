package com.laughing.databindinglaughing;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.laughing.databindinglaughing.bean.User;
import com.laughing.databindinglaughing.databinding.ABaseBinding;
import com.laughing.databindinglaughing.eventbus.A_Event_Bus;

public class BaseActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ABaseBinding binding = DataBindingUtil.setContentView(this, R.layout.a_base);
        User user = new User("fei", "Liang");
        binding.setUser(user);
        Log.e("TAG", "laughing---------------------->   " + binding.getUser().getFirstName());
        Log.e("TAG", "laughing---------------------->   " + binding.getUser().getLastName());
        binding.bt.setOnClickListener(view -> {
            startActivity(new Intent(this, A_Event_Bus.class));
        });
    }
}
