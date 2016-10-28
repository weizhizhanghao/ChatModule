package com.example.think.layoutvarious;

import android.view.View;
import android.widget.ExpandableListView;

import com.example.think.adapter.ExpandAdapter;
import com.example.think.data.ContactInfo;
import com.example.think.data.PersonInfo;

import java.util.ArrayList;
import java.util.List;

public class ExpandViewActivity extends BaseActivity {

    private ExpandableListView mVExpandableList;
    private ExpandAdapter expandAdapter;
    private DataMergeHelper dataMergeHelper;

    @Override
    void initView() {
        super.initView();
        setTitle("通讯录");
        setHeaderShow(true);

        mVExpandableList = (ExpandableListView) findViewById(R.id.expandable_list_view);
        expandAdapter = new ExpandAdapter(this);
        mVExpandableList.setAdapter(expandAdapter);
        mVExpandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                // final PersonInfo data = expandAdapter.getGroupData(groupPosition).getChilds().get(childPosition);
                return true;
            }
        });

        dataMergeHelper = new DataMergeHelper();
        expandAdapter.setDatas(dataMergeHelper.getContacts());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_expand_view;
    }

    private void initPersonData(int branch) {
        for (int i = 0; i < 5; i++) {
            PersonInfo personInfo = new PersonInfo();
            personInfo.setImgUrl("123");
            if (branch == 0) {
                personInfo.setNickname("gift number " + i);
            } else if (branch == 1) {
                personInfo.setNickname("follow " + i);
            } else if (branch == 2) {
                personInfo.setNickname("fans" + i);
            }
        }
    }

    public class DataMergeHelper{
        private List<PersonInfo> giftsList;
        private List<PersonInfo> followList;
        private List<PersonInfo> fansList;

        public void setGiftsList(List<PersonInfo> giftsList) {
            giftsList = giftsList;
        }

        public void setFollowList(List<PersonInfo> followList) {
            followList = followList;
        }

        public void setFansList(List<PersonInfo> fansList) {
            this.fansList = fansList;
        }

        public List<ContactInfo> getContacts(){
            List<ContactInfo> contactInfoList = new ArrayList<>(3);

            ContactInfo item = new ContactInfo();
            item.setGroup("送礼物" + (giftsList == null ? 0 : giftsList.size()));
            item.setChilds(giftsList);
            contactInfoList.add(item);

            item = new ContactInfo();
            item.setGroup("关注" + (followList == null ? 0 : followList.size()));
            item.setChilds(followList);
            contactInfoList.add(item);

            item = new ContactInfo();
            item.setGroup("粉丝" + (fansList == null ? 0 : fansList.size()));
            item.setChilds(fansList);
            contactInfoList.add(item);

            return contactInfoList;
        }
    }
}
