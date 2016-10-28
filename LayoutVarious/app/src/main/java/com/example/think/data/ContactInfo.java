package com.example.think.data;

import java.util.List;

/**
 * Created by HuangMei on 2016/10/27.
 */
public class ContactInfo {
    private String group;
    private List<PersonInfo> childs;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<PersonInfo> getChilds() {
        return childs;
    }

    public void setChilds(List<PersonInfo> childs) {
        this.childs = childs;
    }
}
