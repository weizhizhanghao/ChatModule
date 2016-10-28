package com.example.think.layoutvarious;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.think.adapter.SpinnerAdapter;
import com.example.think.data.PersonInfo;
import com.example.think.view.SpinnerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private SpinnerView spinnerView;
    private ListView listView;
    private SpinnerAdapter spinnerAdapter;
    private List<PersonInfo> personInfoList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    void initView() {
        setHeaderShow(true);
        showBackImg(true);
        super.initView();
        initData();
    }

    protected void initData() {

        setTitle("下拉选择");

        spinnerView = (SpinnerView) findViewById(R.id.m_spinner);
        listView = (ListView) findViewById(R.id.list);

        spinnerView.setItems(new String[]{"送礼物", "关注", "粉丝"});
        spinnerAdapter = new SpinnerAdapter(this);

        listView.setAdapter(spinnerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ExpandViewActivity.class);
                startActivity(intent);
            }
        });
        spinnerView.setOnValueChangedListener(new SpinnerView.OnValueChangedListener() {
            @Override
            public void onValueChanged(String value, int index) {
                initPersonData(index);
                switch (index) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        });
    }

    private void initPersonData(int branch) {
        if (personInfoList.size() > 0) {
            personInfoList.clear();
        }
        for (int i = 0; i < 5; i++) {
            PersonInfo personInfo = new PersonInfo();
            personInfo.setImgUrl("123");
            if (branch == 0) {
                personInfo.setNickname("gift number " + i);
            } else if (branch == 1) {
                personInfo.setNickname("follow " + i);
            } else if (branch == 1) {
                personInfo.setNickname("fans" + i);
            }
            personInfoList.add(personInfo);
        }
        spinnerAdapter.setDatas(personInfoList);
        goToNextFilter(personInfoList);
    }

    private boolean mNextStop = false;

    public void goToNextFilter(final List<PersonInfo> data) {
        if (data != null && data.size() > 0) {
            mNextStop = true;
        }
        if (spinnerView.isLastedChecked()) {
            mNextStop = true;
        }
        if (mNextStop)
            return;
        spinnerView.next();
    }
}
