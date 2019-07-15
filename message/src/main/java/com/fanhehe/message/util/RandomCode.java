package com.fanhehe.message.util;

/**
 * Created by fanhehe on 15/07/2019.
 */
public final class RandomCode {

    private static final int MIN_LENGTH = 4;
    private static final int MAX_LENGTH = 10;

    public enum Type {
        MIXED(1),
        ALPHA(2),
        DIGITAL(3);

        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        Type(int value) {
            this.value = value;
        }
    }

    public static String rand() {
        return rand(Type.DIGITAL, MIN_LENGTH);
    }

    private static String rand(Type type, int length) {

        String result;

        if (type == null) {
            type = Type.DIGITAL;
        }

        if (length < MIN_LENGTH || length > MAX_LENGTH) {
            length = MIN_LENGTH;
        }

        switch (type) {
            case MIXED:
                result = String.valueOf(Math.random()).substring(2, 2 + length);
                break;
            case ALPHA:
                result = String.valueOf(Math.random()).substring(2, 2 + length);
                break;
            case DIGITAL:
                default:
                result = String.valueOf(Math.random()).substring(2, 2 + length);
                break;
        }

        return result;
    }
}
