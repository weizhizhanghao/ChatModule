package com.example.think.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.think.data.PersonInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangMei on 2016/10/28.
 */
public abstract class MBaseExpandListAdapter<T, G extends BaseViewHolder, C extends BaseViewHolder> extends BaseExpandableListAdapter{

    protected Context mContext;
    protected List<T> mDatas = new ArrayList<>();
    protected LayoutInflater mInflater;

    public MBaseExpandListAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setDatas(List<T> datas){
        mDatas = datas;
        notifyDataSetChanged();
    }

    public void setDatas(List<T> datas, boolean isRefresh){
        if (mDatas == null || isRefresh){
            mDatas = datas;
        } else {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public T getGroupData(int groupPosition){
        return mDatas.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mDatas.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        G holder;
        if(convertView == null){
            holder = onCreateGroupViewHolder(groupPosition, parent);
            convertView = holder.rootView;
            convertView.setTag(holder);
        } else {
            holder = (G) convertView.getTag();
        }
        onBindGroupView(holder, groupPosition, isExpanded);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        C holder;
        if (convertView == null){
            holder = onCreateChildViewHolder(groupPosition, childPosition, parent);
            convertView = holder.rootView;
            convertView.setTag(holder);
        } else {
            holder = (C) convertView.getTag();
        }
        onBindChildView(holder, groupPosition, childPosition, isExpanded);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public abstract G onCreateGroupViewHolder(int groupPosition, ViewGroup parent);
    public abstract void onBindGroupView(G holder, int groupPosition, boolean isExpanded);
    public abstract C onCreateChildViewHolder(int groupPosition, int childPosition, ViewGroup parent);
    public abstract void onBindChildView(C holder, int groupPosition, int childPosition, boolean isExpanded);
}
