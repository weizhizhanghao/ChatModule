package com.example.think.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.example.think.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/*
* this activity is a test about expansion and contraction of the Toolbar
* */

public class CoordinatorLayoutActivity2 extends AppCompatActivity {

    private View view1, view2;
    private ViewPager viewPager;
    private List<View> viewList;
    private TabLayout tabs;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    PagerAdapter mPagerAdapter;

    private NoScrollListView listView;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);getSupportActionBar().hide();
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_coordinator_layout2);
       // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
             //   WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initViewPager();
        initListView();

    }

    void initListView(){

        listView = (NoScrollListView)view1.findViewById(R.id.list_view);
        mDatas = new ArrayList<>();
        for(int i = 1; i <= 50 ; i++) {
            mDatas.add("AndroidGet " + i + " ac " + " 2015-11-11 "+" 1884896 "+ i+ i + i);
        }
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDatas));
    }

    void initViewPager(){

        mTitleList.add("No:1");
        mTitleList.add("No:2");
        mTitleList.add("No:3");
        mTitleList.add("No:4");
        mTitleList.add("No:5");

        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setBackgroundColor(this.getResources().getColor(R.color.shadow_end_color));

        tabs.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        tabs.addTab(tabs.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        tabs.addTab(tabs.newTab().setText(mTitleList.get(1)));
        mPagerAdapter = new pagerAdapter();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater layoutInflater = getLayoutInflater();
        view1 = layoutInflater.inflate(R.layout.view1_layout, null);
        view2 = layoutInflater.inflate(R.layout.view2_layout, null);

        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setCurrentItem(0, true);
        tabs.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabs.setTabsFromPagerAdapter(mPagerAdapter);//给Tabs设置适配器
    }

    public class pagerAdapter extends PagerAdapter{
        public pagerAdapter() {
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);//页卡标题
        }
    }
}
