package com.fanhehe.user.controller;

import com.fanhehe.user.util.Crypto;
import com.fanhehe.user.util.Regexp;
import com.fanhehe.user.dto.CaptchaCode;
import com.fanhehe.user.constant.BindEnum;
import com.fanhehe.user.service.CaptchaService;
import com.fanhehe.user.service.HandleUserService;
import com.fanhehe.util.result.IResult;
import com.fanhehe.util.result.InvokeResult;

import java.time.ZoneId;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;



@RestController
public class RegisterController {

    @Value("${time-zone}")
    private String zone;

    private CaptchaService captchaService;
    private HandleUserService handleUserService;

    @Autowired
    @Qualifier("Impl.CaptchaService")
    public void setCaptchaService(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @Autowired
    @Qualifier("Impl.UserService")
    public void setHandleUserService(HandleUserService handleUserService) {
        this.handleUserService = handleUserService;
    }

    /**
     * 使用邮箱进行注册
     * @param email 邮箱地址
     * @param password 登录密码
     * @param captcha 验证码
     * @return 注册结果
     */
    @ResponseBody
    @RequestMapping(value = "/api/user/register/email", method = RequestMethod.GET)
    public IResult registerByEmail(
            @Validated
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(defaultValue = "") String captcha
    ) {

        if (!Regexp.isEmail(email)) {
            return InvokeResult.failure("非合法邮箱");
        }

        IResult<CaptchaCode> verify =  captchaService.verifyEmailCaptcha(email, captcha);

        if (verify.isFailure()) {
            return InvokeResult.failure(verify.getMessage(), verify.getCode());
        }

        IResult user = handleUserService.handleMakeNewUser(email, password, BindEnum.EMAIL);

        if (user.isSuccess()) {
            return user;
        }

        return InvokeResult.failure(user.getMessage(), user.getCode());
    }

    /**
     * 使用手机号进行注册
     * @param phone 手机号
     * @param password 密码
     * @param captcha 验证码
     * @return 返回注册结果
     */
    @RequestMapping(value = "/api/user/register/phone", method = RequestMethod.GET)
    public IResult registerByPhone(
            @RequestParam String phone,
            @RequestParam String password,
            @RequestParam(defaultValue = "") String captcha
    ) {
        return InvokeResult.failure("暂不支持手机号注册");
        // 实际可用注册，但不对外提供
        // return handleUserService.handleMakeNewUser(phone, password, BindEnum.PHONE);
    }

    /**
     * 发送邮箱验证码结果
     * @param email 手机号
     * @return 发送验证码结果
     */
    @RequestMapping(value = "/api/user/captcha/email", method = RequestMethod.GET)
    public IResult<CaptchaCode> sendRegisterEmail(
            @RequestParam String email
    ) {

        String now = Instant
                .now()
                .atZone(ZoneId.of(zone))
                .format(DateTimeFormatter.ofPattern("yyyy_MM_dd_H_mm"));

        String orderId = String.join("_", now, Crypto.md5(email));

        return captchaService.sendEmailCaptcha(email, orderId);
    }

    /**
     * 发送手机验证码
     * @param phone 手机号
     * @return 发送验证码结果
     */
    @RequestMapping(value = "/api/user/captcha/phone", method = RequestMethod.GET)
    public IResult<CaptchaCode> sendRegisterPhone(
            @RequestParam(defaultValue = "") String phone
    ) {
        return InvokeResult.failure("暂不支持手机号发送验证码");
    }

}
