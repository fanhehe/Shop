package com.fanhehe.message.constant;

public enum  MessageStatus {
    INIT(1),
    SENDING(2),
    FAILURE(3),
    SUCCESS(4);

    private int value;

    MessageStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
