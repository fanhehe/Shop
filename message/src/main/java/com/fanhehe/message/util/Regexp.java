package com.fanhehe.message.util;

import java.util.regex.Pattern;
import com.fanhehe.message.constant.PhoneRegularExp;

public final class Regexp {

    private static final String EMAIL = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    public static boolean isEmail(String email) {
        return email != null && Pattern.compile(EMAIL).matcher(email).matches();
    }

    public static boolean isPhone(String phone) {
        for (PhoneRegularExp exp: PhoneRegularExp.values()) {
            Pattern pattern = Pattern.compile(exp.getRegularExp());

            if (pattern.matcher(phone).matches()) {
                return true;
            }
        }

        return false;
    }
}
