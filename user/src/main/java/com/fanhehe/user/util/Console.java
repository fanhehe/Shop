package com.fanhehe.user.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Console {

    private static Logger logger = LoggerFactory.getLogger(Console.class);

    public static void log(Object o) {
        if (o == null) {
            return;
        }

        logger.info(o.toString());
    }
}
