package com.fanhehe.backend.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.fanhehe.backend.dto.User;
import com.fanhehe.util.constant.AccountType;
import com.fanhehe.util.http.HttpUtil;
import com.fanhehe.backend.service.UserService;
import com.fanhehe.util.result.IResult;
import com.fanhehe.util.result.InvokeResult;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service("Impl.UserService")
public class UserServiceImpl extends HttpUtil<User> implements UserService {

    @Value("${com.fanhehe.module.user}")
    String endpoint;

    @Override
    public String getEndpoint() {
        return this.endpoint;
    }

    public IResult<User> doRegister(String target, AccountType accountType, String password, Map<String, String> properties) {

        String path;
        properties.put("target", target);
        properties.put("password", password);
        properties.put("accountType", String.valueOf(accountType.getValue()));

        switch (accountType) {
            case EMAIL:
                path = "/api/user/register/email";
                break;
            case PHONE:
                path = "/api/user/register/phone";
                break;
            default:
                return InvokeResult.failure("不支持当前注册类型");
        }

        return this.post(path, properties);
    }

    public IResult sendCaptcha(String target, AccountType accountType) {

        String path;
        Map<String, String> properties = new HashMap<>();

        properties.put("target", target);
        properties.put("accountType", String.valueOf(accountType.getValue()));

        switch (accountType) {
            case EMAIL:
                path = "/api/user/captcha/email";
                break;
            case PHONE:
                path = "/api/user/captcha/phone";
                break;
            default:
                return InvokeResult.failure("不支持当前注册类型");
        }

        return this.post(path, properties);
    }
}
