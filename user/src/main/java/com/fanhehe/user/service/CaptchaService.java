package com.fanhehe.user.service;

import com.fanhehe.util.result.IResult;
import com.fanhehe.user.dto.CaptchaCode;

public interface CaptchaService {

    String APP = "User-Common-CaptchaService";

    IResult<CaptchaCode> sendEmailCaptcha(String email, String app, String orderId);
    IResult<CaptchaCode> verifyEmailCaptcha(String email, String app, String captcha);

    default IResult<CaptchaCode> sendEmailCaptcha(String email, String orderId) {
        return sendEmailCaptcha(email, getApp(), orderId);
    }

    default IResult<CaptchaCode> verifyEmailCaptcha(String email, String captcha) {
        return verifyEmailCaptcha(email, getApp(), captcha);
    }

    default String getApp() {
        return APP;
    }
}
