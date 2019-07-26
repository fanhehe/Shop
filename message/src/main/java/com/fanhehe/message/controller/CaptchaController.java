package com.fanhehe.message.controller;

import com.fanhehe.message.util.Regexp;
import com.fanhehe.message.dto.Receiver;
import com.fanhehe.message.model.CaptchaCode;
import com.fanhehe.message.constant.ReceiverType;
import com.fanhehe.message.service.CaptchaService;

import com.fanhehe.util.result.IResult;
import com.fanhehe.util.result.InvokeResult;
import com.fanhehe.util.constant.response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class CaptchaController {

    private CaptchaService captchaService;

    @Autowired
    @Qualifier("Impl.EmailCaptchaService")
    public void setCaptchaService(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @RequestMapping(value = "/api/message/captcha/email/send", method = RequestMethod.POST)
    public IResult<CaptchaCode> sendCaptchaByEmail(
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "") String app,
            @RequestParam(defaultValue = "") String orderId
    ) {
        if (!Regexp.isEmail(email)) {
            return InvokeResult.failure(MessageResponse.INVAID_EMAIL);
        }

        if (app == null || orderId == null || "".equals(app) || "".equals(orderId)) {
            return InvokeResult.failure(MessageResponse.EMPTY_PARAMS);
        }

        Receiver receiver = new Receiver();

        receiver.setTarget(email);
        receiver.setReceiverType(ReceiverType.EMAIL);

        CaptchaCode captchaCode = new CaptchaCode();

        captchaCode.setApp(app);
        captchaCode.setTarget(email);
        captchaCode.setOrderId(orderId);

        return captchaService.send(receiver, captchaCode);
    }

    @RequestMapping(value = "/api/message/captcha/email/verify", method = RequestMethod.POST)
    public IResult<CaptchaCode> verifyCaptchaByEmail(
            @RequestParam(defaultValue = "") String app,
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "") String captcha
    ) {

        Receiver receiver = new Receiver();

        receiver.setTarget(email);
        receiver.setReceiverType(ReceiverType.EMAIL);

        CaptchaCode captchaCode = new CaptchaCode();

        captchaCode.setApp(app);
        captchaCode.setTarget(email);
        captchaCode.setCode(captcha);

        return captchaService.verify(receiver, captchaCode);
    }
}
