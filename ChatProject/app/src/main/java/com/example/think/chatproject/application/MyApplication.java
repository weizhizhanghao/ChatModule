package com.example.think.chatproject.application;

import android.app.Application;

import cn.smssdk.SMSSDK;

/**
 * Created by HuangMei on 2016/11/2.
 */
public class MyApplication extends Application{
    private static MyApplication myApplication;

    public static MyApplication getMyApplication(){
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        SMSSDK.initSDK(this, "189dceee7b11d", "95e9d06e26272ce38c35cff9bdbf86d4");
    }
}
