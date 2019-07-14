package com.fanhehe.message.model;

import javax.persistence.Table;
import org.springframework.stereotype.Repository;

@Repository
@Table(name = "message.captcha_auth")
public class CaptchaAuth {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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
    private String app;
    private int type;
    private String target;
    private int createdAt;
    private int updatedAt;
}

//    CREATE TABLE `captcha_auth` (
//        `id` int(4) NOT NULL AUTO_INCREMENT COMMENT 'id',
//        `app` varchar(40) NOT NULL COMMENT '区分不同渠道下发的消息',
//        `type` tinyint(1) DEFAULT '1' COMMENT '1: 牛牛号, 2: 手机号, 3邮箱',
//        `target` varchar(64) NOT NULL COMMENT '牛牛号/hash(区号 + 手机号)/邮箱',
//        `createdAt` int(4) DEFAULT '0' COMMENT '创建记录时间',
//        `updatedAt` int(4) DEFAULT '0' COMMENT '更新记录时间',
//        PRIMARY KEY (`id`),
//        UNIQUE KEY `idx_app_target` (`app`,`target`)
//        ) ENGINE=InnoDB AUTO_INCREMENT=591 DEFAULT CHARSET=utf8 COMMENT='验证码auth表'