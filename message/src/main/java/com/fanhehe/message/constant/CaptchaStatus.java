package com.fanhehe.message.constant;

public enum  CaptchaStatus {
    INIT(1),
    SENDING(2),
    SUCCESS(3),
    FAILURE(4);

    private int value;

    CaptchaStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
