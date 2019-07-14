package com.fanhehe.message.dto;

import com.fanhehe.message.constant.ReceiverType;

public class Receiver {
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public ReceiverType getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(ReceiverType receiverType) {
        this.receiverType = receiverType;
    }

    private String target;
    private ReceiverType receiverType;
}
