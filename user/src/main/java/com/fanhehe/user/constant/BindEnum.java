package com.fanhehe.user.constant;

public enum BindEnum {
    EMAIL(1),
    PHONE(2),
    WECHAT(3),
    SINA(4),
    GITHUB(5),
    TYPE_FACEBOOK(6);

    private final int value;

    BindEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BindEnum getBindEnumByValue(int value) {
        for (BindEnum item: BindEnum.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }

        return null;
    }
}
