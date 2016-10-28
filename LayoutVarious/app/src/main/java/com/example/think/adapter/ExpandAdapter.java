package com.example.think.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.think.data.ContactInfo;
import com.example.think.data.PersonInfo;
import com.example.think.layoutvarious.R;
import com.example.think.util.VariousUtils;

import java.util.List;

/**
 * Created by HuangMei on 2016/10/28.
 */
public class ExpandAdapter extends MBaseExpandListAdapter<ContactInfo,ExpandAdapter.GroupViewHolder, ExpandAdapter.ChildViewHolder>{

    public ExpandAdapter(Context context) {
        super(context);
    }

    @Override
    public GroupViewHolder onCreateGroupViewHolder(int groupPosition, ViewGroup parent) {
        return new GroupViewHolder(mInflater.inflate(R.layout.expand_group_layout, null));
    }

    @Override
    public void onBindGroupView(GroupViewHolder holder, int groupPosition, boolean isExpanded) {
        final ContactInfo contactInfo = getGroupData(groupPosition);

        if (isExpanded){
            holder.v_group_indicator.setRotation(90.0f);
        } else {
            holder.v_group_indicator.setRotation(0.0f);
        }
        holder.v_title.setText(contactInfo.getGroup());
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(int groupPosition, int childPosition, ViewGroup parent) {
        return new ChildViewHolder(mInflater.inflate(R.layout.chat_item, null));
    }

    @Override
    public void onBindChildView(ChildViewHolder holder, int groupPosition, int childPosition, boolean isExpanded) {
        final PersonInfo personInfo = getChildData(groupPosition, childPosition);

        if (!TextUtils.isEmpty(personInfo.getImgUrl())) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ic_launcher);
            holder.v_avatar.setImageBitmap(VariousUtils.drawableToBitmap(drawable));
        } else {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ic_launcher);
            holder.v_avatar.setImageBitmap(VariousUtils.drawableToBitmap(drawable));
        }
        if (!TextUtils.isEmpty(personInfo.getNickname())) {
            holder.v_nickname.setText(personInfo.getNickname());
        } else {
            holder.v_nickname.setText("no name");
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ContactInfo contactInfo = mDatas.get(groupPosition);
        if (contactInfo != null){
            List<PersonInfo> childern = contactInfo.getChilds();
            return childern == null ? 0 : childern.size();
        }
        return 0;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition){
        return mDatas.get(groupPosition).getChilds().get(childPosition);
    }

    public PersonInfo getChildData(int groupPosition, int childPosition){
        return mDatas.get(groupPosition).getChilds().get(childPosition);
    }

    public static class GroupViewHolder extends BaseViewHolder{
        public final View v_group_indicator;
        public final TextView v_title;

        public GroupViewHolder(View convertView) {
            super(convertView);
            v_group_indicator = rootView.findViewById(R.id.group_indicator);
            v_title = (TextView)rootView.findViewById(R.id.expand_title);
        }
    }

    public static class ChildViewHolder extends BaseViewHolder{

        public final ImageView v_avatar;
        public final TextView v_nickname;

        public ChildViewHolder(View rootView) {
            super(rootView);
            v_avatar = (ImageView) rootView.findViewById(R.id.avatar);
            v_nickname = (TextView) rootView.findViewById(R.id.nick_name);
        }
    }
}
