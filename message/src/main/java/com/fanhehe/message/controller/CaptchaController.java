package com.fanhehe.message.controller;

import java.util.HashMap;
import com.fanhehe.message.util.IResult;
import com.fanhehe.message.dto.bind.Bind;
import com.fanhehe.message.constant.BindEnum;
import com.fanhehe.message.service.BindService;
import com.fanhehe.message.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class CaptchaController {

    BindService bindService;
    CaptchaService captchaService;

    @Autowired
    @Qualifier("Impl.Common.BindService")
    public void setBindService(BindService bindService) {
        this.bindService = bindService;
    }

    @Autowired
    public void setCaptchaService(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @RequestMapping(value = "/api/message/captcha/email/send", method = RequestMethod.GET)
    public IResult<Bind> sendCaptchaByEmail(
            @RequestParam(defaultValue = "") int uid,
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "") String app,
            @RequestParam(defaultValue = "") String orderId,
            @RequestParam HashMap<String, String> options
    ) {
        return bindService
                .getBindByUid(uid, BindEnum.EMAIL);
    }

    @RequestMapping(value = "/api/message/captcha/email/verify", method = RequestMethod.GET)
    public void verifyCaptchaByEmail(
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "") String captcha,
            @RequestParam HashMap<String, String> options
    ) {

    }
}