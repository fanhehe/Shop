package com.fanhehe.message.dto;


import com.fanhehe.message.constant.MessageCategory;

public class BaseMessage {

    private String app;
    private String content;
    private String orderId;
    private int duration;
    private MessageCategory category;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrderId() {
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

    public MessageCategory getCategory() {
        return category;
    }

    public void setCategory(MessageCategory category) {
        this.category = category;
    }
}
