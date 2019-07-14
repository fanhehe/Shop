package com.fanhehe.user.util;

import java.time.Instant;

/**
 * Created by fanhehe on 10/07/2019.
 */
public final class Time {

    public static int makeUnixTimestamp() {
        return (int) (Instant.now().toEpochMilli() / 1000);
    }
}
