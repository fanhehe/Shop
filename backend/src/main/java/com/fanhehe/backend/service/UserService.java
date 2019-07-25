package com.fanhehe.backend.service;

import java.util.Map;
import java.util.HashMap;


import com.fanhehe.util.type.Type;
import com.fanhehe.backend.dto.User;
import com.fanhehe.util.result.IResult;
import com.fanhehe.util.result.InvokeResult;
import com.fanhehe.util.constant.AccountType;

public interface UserService {

    default IResult sendCaptcha(String target) {
        if (Type.isEmail(target)) {
            return sendCaptcha(target, AccountType.EMAIL);
        } else if (Type.isPhone(target)) {
            return sendCaptcha(target, AccountType.PHONE);
        }

        return InvokeResult.failure("不支持当前注册方式");
    }

    IResult sendCaptcha(String target, AccountType accountType);

    default IResult<User> doRegister(String target, String password, String captcha) {
        if (Type.isEmail(target)) {
            return doRegister(target, AccountType.EMAIL, password, captcha);
        } else if (Type.isPhone(target)) {
            return doRegister(target, AccountType.PHONE, password, captcha);
        }

        return InvokeResult.failure("不支持当前注册方式");
    }

    default IResult<User> doRegister(String target, AccountType accountType, String password, String captcha) {

        Map<String, String> hashMap = new HashMap<>();

        hashMap.put("captcha", captcha);

        return doRegister(target, accountType, password, hashMap);
    }

    IResult<User> doRegister(String target, AccountType accountType, String password, Map<String, String> properties);
}
