package com.fanhehe.backend.controller.auth;

import com.fanhehe.backend.dto.User;
import com.fanhehe.util.result.IResult;
import org.springframework.util.Assert;
import com.fanhehe.backend.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin(value = "*", allowCredentials = "true")
public class RegisterController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/api/auth/captcha", method = RequestMethod.POST)
    public IResult captcha(
        @RequestParam(name = "target", defaultValue = "") String target
    ) {
        return userService.sendCaptcha(target);
    }

    @RequestMapping(path = "/api/auth/register", method = RequestMethod.POST)
    public IResult<User> register(
        @RequestParam(name = "target", defaultValue = "") String target,
        @RequestParam(name = "password", defaultValue = "") String password,
        @RequestParam(name = "captcha", defaultValue = "") String captcha
    ) {

        Assert.notNull(target, "用户名不能为null");
        Assert.notNull(captcha, "验证码不能为null");
        Assert.notNull(password, "用户密码不能为null");

        return userService.doRegister(target, password, captcha);
    }
}
