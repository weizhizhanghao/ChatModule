package com.example.think.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangMei on 2016/10/27.
 */
public abstract class MBaseAdapter<T, K extends BaseViewHolder> extends BaseAdapter {

    protected Context mContext;
    private List<T> mDatas;
    protected LayoutInflater layoutInflater;
    private DataWatcher mDataWatcher;

    public MBaseAdapter(Context context) {
        this.mContext = context;
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setDatas(List<T> datas) {
        if (datas != null) {
            mDatas = new ArrayList<>(datas.size());
            mDatas.addAll(datas);
        } else {
            mDatas = null;
        }
        notifyDataSetChanged();
    }

    public void setDatas(List<T> datas, boolean isRefresh) {
        if (mDatas == null || isRefresh) {
            mDatas = new ArrayList<>();
        }

        if (datas != null) {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public T getData(int position) {
        return mDatas.get(position);
    }

    public void removeData(T data) {
        mDatas.remove(data);
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        if (mDataWatcher != null) {
            mDataWatcher.onDataChanged(getCount());
        }
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        K holder;
        if (view == null) {
            holder = onCreateViewHolder(i, viewGroup);
            view = holder.rootView;
            view.setTag(holder);
        } else {
            holder = (K) view.getTag();
        }
        onBindViewHolder(holder, i);
        return view;
    }

    public abstract K onCreateViewHolder(int position, ViewGroup viewGroup);

    public abstract void onBindViewHolder(K holder, int position);

    public interface DataWatcher {
        void onDataChanged(int count);
    }

    public void setDataWatche(DataWatcher dataWatche) {
        this.mDataWatcher = dataWatche;
    }
}
