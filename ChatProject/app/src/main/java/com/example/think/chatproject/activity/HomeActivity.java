package com.example.think.chatproject.activity;

import android.os.Bundle;

import com.example.think.chatproject.R;

import cn.smssdk.SMSSDK;

public class HomeActivity extends BaseActivity {

    private static HomeActivity homeActivity;

    public static HomeActivity getHomeActivity() {
        return homeActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeActivity = this;
        setContentView(R.layout.activity_main);
        SMSSDK.getVerificationCode("+86", "15351244815");
    }
}
