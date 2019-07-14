package com.fanhehe.message.model;

import org.springframework.stereotype.Repository;

@Repository
public class Message {



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessage_payload() {
        return message_payload;
    }

    public void setMessage_payload(String message_payload) {
        this.message_payload = message_payload;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public String getOrderId(){
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public int getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(int receiverType) {
        this.receiverType = receiverType;
    }

    private int id;
    private int uid;
    private int category;
    private int status;

    private String app;
    private String receiver;

    private int receiverType;
    private String title;
    private String content;
    private String message_payload;
    private String attachments;
    private String orderId;
    private int duration;
    private int createdAt;
    private int updatedAt;


}
//    CREATE TABLE `message` (
//        `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
//        `app` varchar(50) DEFAULT '' COMMENT '服务权限配置系统分配给应用的 app',
//        `category` tinyint(1) NOT NULL COMMENT '消息种类: 0-系统消息, 1-短信, 2-邮件',
//        `receiver` varchar(80) DEFAULT '' COMMENT '真实发送的地方, 用户ID, 邮箱地址, 手机号',
//        `receiverType` tinyint(1) NOT NULL COMMENT 'receiver的类型 ',
//        `uid` int(4) DEFAULT 0 COMMENT '消息接收者牛牛号',
//        `status` tinyint(4) DEFAULT 1 COMMENT '消息发送状态（1-初始化，2-失败，3-成功, 4-过期）',
//        `title` varchar(50) DEFAULT '' COMMENT '标题',
//        `content` mediumtext COMMENT '消息内容, 加密存储，Impush 的内容包含 desc 等字段，json 序列化后再加密存储',
//        `messagePayload` mediumtext  COMMENT '消息的附带信息',
//        `attachments` text COMMENT '附件信息，json 数组格式',
//        `orderId` varchar(80) DEFAULT '0' COMMENT '订单id',
//        `duration` int(10) DEFAULT '86400' COMMENT '消息有效时间',
//        `createdAt` int(4) DEFAULT NULL COMMENT '流水创建时间',
//        `updatedAt` int(4) DEFAULT NULL COMMENT '流水更新时间',
//        PRIMARY KEY (`id`),
//        KEY `idx_app` (`app`),
//        KEY `idx_uid` (`uid`),
//        KEY `idx_receiver` (`receiver`)
//        ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;