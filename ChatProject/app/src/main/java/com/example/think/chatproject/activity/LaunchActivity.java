package com.example.think.chatproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.think.chatproject.R;

public class LaunchActivity extends AppCompatActivity {

    private boolean isLoginStatus = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        intentLaunch(isLoginStatus);
    }

    private void intentLaunch(boolean loginStatus){
        Intent intent = new Intent();
        BaseActivity baseActivity;
        if (loginStatus){
            baseActivity = new HomeActivity();
        } else {
            baseActivity = new LoginActivity();
        }
        intent.setClass(this,baseActivity.getClass());
        startActivity(intent);
    }
}
