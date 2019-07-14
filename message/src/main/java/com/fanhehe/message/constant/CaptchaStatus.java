package com.fanhehe.message.constant;

public enum  CaptchaStatus {
    INIT(1),
    SUCCESS(2),
    FAILURE(3);

    private int value;

    CaptchaStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
