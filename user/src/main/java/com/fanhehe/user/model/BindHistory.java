package com.fanhehe.user.model;

import org.springframework.stereotype.Repository;

@Repository
public class BindHistory {

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

//    CREATE TABLE `bind_history` (
//        `id` int(4) NOT NULL AUTO_INCREMENT,
//        `uid` int(4) NOT NULL COMMENT '用户ID',
//        `type` varchar(128) NOT NULL COMMENT '绑定信息类型',
//        `openid` varchar(128) DEFAULT '0' COMMENT '第三方ID',
//        `credential` varchar(32) DEFAULT '0' COMMENT '凭证',
//        `createdAt` int(4) DEFAULT '0' COMMENT '创建记录时间',
//        `updatedAt` int(4) DEFAULT '0' COMMENT '更新记录时间',
//        PRIMARY KEY (`id`)
//        ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='用户绑定信息表';
