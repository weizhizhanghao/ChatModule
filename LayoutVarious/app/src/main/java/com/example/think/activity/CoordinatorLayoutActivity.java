package com.example.think.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.example.think.adapter.PersonAdapter;
import com.example.think.data.Person;

import java.util.ArrayList;
import java.util.List;

/*
* this activity is a test about expansion, contraction of the Toolbar, floating action buttons and Snack bar
* */

public class CoordinatorLayoutActivity extends AppCompatActivity implements PersonAdapter.OnRecyclerViewListener {
    public RecyclerView recyclerView;
    PersonAdapter personAdapter;
    List<Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_coordinator_layout);
        initData();

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Title");

        recyclerView = (RecyclerView) findViewById(R.id.rvToDoList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        personAdapter = new PersonAdapter(personList);
        personAdapter.setOnRecyclerViewListener(this);
        recyclerView.setAdapter(personAdapter);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "FAB", Snackbar.LENGTH_LONG)
                        .setAction("cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除Action后的响应事件

                            }
                        }).show();
            }
        });
    }

    void initData(){
        for(int i = 0; i < 100; i ++){
            Person person = new Person();
            person.setAge(i + "");
            person.setName("number " + i + "name" + i);
            personList.add(person);
        }
     }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, CoordinatorLayoutActivity2.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }
}
