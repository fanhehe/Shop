package com.fanhehe.message.util;

import java.time.Instant;

public final class Time {

    public static int makeUnixTimestamp() {
        return (int) (Instant.now().toEpochMilli() / 1000);
    }
}
