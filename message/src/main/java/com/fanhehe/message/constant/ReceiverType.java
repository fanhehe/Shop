package com.fanhehe.message.constant;

/**
 * Created by fanhehe on 11/07/2019.
 */
public enum ReceiverType {
    UID(1),
    PHONE(2),
    EMAIL(3);

    private int value;

    ReceiverType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
