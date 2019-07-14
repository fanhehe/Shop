package com.fanhehe.message.dto.bind;

import org.springframework.stereotype.Repository;

@Repository
public class Bind {

    public final static int TYPE_PHONE = 1;
    public final static int TYPE_EMAIL = 2;
    public final static int TYPE_WECHAT = 3;
    public final static int TYPE_SINA = 4;
    public final static int TYPE_GITHUB = 5;
    public final static int TYPE_FACEBOOK = 6;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public int getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    private int uid;

    private int type;
    private String openid;
    private String credential;

    private int createdAt;
    private int updatedAt;
}
