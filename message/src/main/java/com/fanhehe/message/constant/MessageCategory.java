package com.fanhehe.message.constant;

public enum MessageCategory {
    System(1),
    PHONE(2),
    EMAIL(3);

    private int value;

    private MessageCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
