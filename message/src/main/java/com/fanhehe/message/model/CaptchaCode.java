package com.fanhehe.message.model;


import org.springframework.stereotype.Repository;

@Repository
public class CaptchaCode {

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

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
    private int auth;
    private String app;
    private String orderId;

    private int overtime;
    private String code;
    private int status;
    private int createdAt;
    private int updatedAt;
}

//    CREATE TABLE `captcha_code` (
//        `id` int(4) NOT NULL AUTO_INCREMENT COMMENT 'id',
//        `uid` int(4) NOT NULL COMMENT '牛牛号',
//        `auth` int(4) NOT NULL COMMENT 'auth.id',
//        `app` varchar(40) DEFAULT '' COMMENT '类别',
//        `orderId` varchar(40) NOT NULL COMMENT '最终使用的订单号',
//        `overtime` int(4) DEFAULT '300' COMMENT '邮件验证码的有效时间，单位秒, 写于发消息时的配置',
//        `code` varchar(40) NOT NULL COMMENT '发送的消息验证码信息',
//        `status` tinyint(1) DEFAULT '1' COMMENT '发送状态1: 发送中，2: 发送完成, 其他均为失败',
//        `createdAt` int(4) DEFAULT '0' COMMENT '创建记录时间',
//        `updatedAt` int(4) DEFAULT '0' COMMENT '更新记录时间',
//        PRIMARY KEY (`id`),
//        UNIQUE KEY `orderId` (`orderId`),
//        KEY `idx_uid_atuh_code` (`auth`,`code`,`status`,`app`)
//        ) ENGINE=InnoDB AUTO_INCREMENT=607 DEFAULT CHARSET=utf8 COMMENT='验证码 验证码表'
