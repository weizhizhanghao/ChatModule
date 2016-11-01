package com.example.think.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.think.data.PersonInfo;
import com.example.think.activity.R;
import com.example.think.util.VariousUtils;

/**
 * Created by HuangMei on 2016/10/27.
 */
public class SpinnerAdapter extends MBaseAdapter<PersonInfo, SpinnerAdapter.ContentViewHolder> {

    public SpinnerAdapter(Context context) {
        super(context);
    }

    @Override
    public ContentViewHolder onCreateViewHolder(int position, ViewGroup viewGroup) {
        return new ContentViewHolder(layoutInflater.inflate(R.layout.chat_item, null));
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        final PersonInfo data = getData(position);
        if (!TextUtils.isEmpty(data.getImgUrl())) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ic_launcher);
            holder.v_avatar.setImageBitmap(VariousUtils.drawableToBitmap(drawable));
        } else {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ic_launcher);
            holder.v_avatar.setImageBitmap(VariousUtils.drawableToBitmap(drawable));
        }
        if (!TextUtils.isEmpty(data.getNickname())) {
            holder.v_nickname.setText(data.getNickname());
        } else {
            holder.v_nickname.setText("no name");
        }
    }

    static class ContentViewHolder extends BaseViewHolder {
        public final ImageView v_avatar;
        public final TextView v_nickname;

        public ContentViewHolder(View rootView) {
            super(rootView);
            v_avatar = (ImageView) rootView.findViewById(R.id.avatar);
            v_nickname = (TextView) rootView.findViewById(R.id.nick_name);
        }
    }


}
