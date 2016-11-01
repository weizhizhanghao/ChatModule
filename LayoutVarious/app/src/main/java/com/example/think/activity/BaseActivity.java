package com.example.think.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class BaseActivity extends Activity implements View.OnClickListener{

    private View main_header;
    private TextView activityTitle;
    private RelativeLayout backLayout;
    private RelativeLayout menuLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentView());
        main_header = findViewById(R.id.main_header);
        activityTitle = (TextView)findViewById(R.id.activity_name);
        backLayout = (RelativeLayout) findViewById(R.id.main_header_iv_back);
        menuLayout = (RelativeLayout) findViewById(R.id.main_header_right_img_btn_container);

        backLayout.setOnClickListener(this);
        menuLayout.setOnClickListener(this);

        initView();
    }


    void initView(){

    }

    void setTitle(String s){
        activityTitle.setText(s);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_header_iv_back:
                finish();
                break;
            case R.id.main_header_right_img_btn_container:
                clickRightButton();
                break;

        }
    }

    protected void showBackImg(boolean isShow){
        if(isShow){
            backLayout.setVisibility(View.VISIBLE);
        } else {
            backLayout.setVisibility(View.GONE);
        }
    }

    protected void setHeaderShow(boolean isShow){
        if(isShow){
            main_header.setVisibility(View.VISIBLE);
        } else {
            main_header.setVisibility(View.GONE);
        }
    }

    protected void clickRightButton(){
    }

    protected abstract int getContentView();

}
