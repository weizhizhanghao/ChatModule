package com.example.think.data;

import java.io.Serializable;

/**
 * Created by HuangMei on 2016/10/27.
 */
public class PersonInfo implements Serializable{
    String imgUrl;
    String nickname;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
