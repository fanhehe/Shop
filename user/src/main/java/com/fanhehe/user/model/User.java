package com.fanhehe.user.model;

import org.springframework.stereotype.Repository;

/**
 * 用户表
 */
@Repository
public class User {

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    private int id;
    private int uid;

    private String nick;
    private String avatar;
    private String password;

    private int createdAt;
    private int updatedAt;
}

//    CREATE TABLE `user` (
//        `id` int(4) NOT NULL AUTO_INCREMENT,
//        `uid` int(4) NOT NULL COMMENT '用户ID',
//        `nick` varchar(128) NOT NULL COMMENT '用户昵称',
//        `avatar` varchar(128) DEFAULT '0' COMMENT '用户头像',
//        `password` varchar(32) DEFAULT '0' COMMENT '用户密码',
//        `createdAt` int(4) DEFAULT '0' COMMENT '创建记录时间',
//        `updatedAt` int(4) DEFAULT '0' COMMENT '更新记录时间',
//        PRIMARY KEY (`id`),
//        UNIQUE KEY `udx_uid` (`uid`)
//        ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='用户表';