package com.fanhehe.backend.dto;

import org.springframework.stereotype.Repository;

/**
 * 用户表
 */
@Repository
public class User {

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private int uid;
    private String nick;
    private String avatar;
}

