package com.fanhehe.user.controller;

import java.util.HashMap;

import com.fanhehe.user.util.IResult;
import com.fanhehe.user.util.InvokeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fanhehe.user.model.User;
import com.fanhehe.user.constant.BindEnum;
import com.fanhehe.user.service.HandleUserService;
import com.fanhehe.user.util.Regexp;
import com.fanhehe.user.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    private HandleUserService handleUserService;
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    @Qualifier("Impl.UserService")
    public void setHandleUserService(HandleUserService handleUserService) {
        this.handleUserService = handleUserService;
    }

    @ResponseBody
    @RequestMapping(value = "/api/user/register/email", method = RequestMethod.GET)
    public IResult registerByEmail(
            @Validated
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(defaultValue = "") String captcha
    ) {
        HashMap<String, String> property = new HashMap<>();

        if (!Regexp.isEmail(email)) {
            return InvokeResult.failure("非合法邮箱");
        }

        IResult user = handleUserService.handleMakeNewUser(email, password, BindEnum.EMAIL, property);

        if (user.isSuccess()) {
            return user;
        }

        return InvokeResult.failure(user.getMessage(), user.getCode());
    }

    @RequestMapping(value = "/api/user/register/phone", method = RequestMethod.GET)
    public IResult registerByPhone(
            @RequestParam String phone,
            @RequestParam String password,
            @RequestParam(defaultValue = "") String captcha
    ) {
        HashMap<String, String> property = new HashMap<>();
        return handleUserService.handleMakeNewUser(phone, password, BindEnum.PHONE, property);
    }

    @RequestMapping(value = "/api/user/captcha/phone", method = RequestMethod.GET)
    public User sendRegisterPhone(
            @RequestParam String phone
    ) {
        return null;
//        return handleUserService.handleMakeNewUser(phone, BindEnum.PHONE);
    }

}