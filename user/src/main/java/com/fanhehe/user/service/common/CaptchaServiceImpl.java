package com.fanhehe.user.service.common;

import java.util.Map;
import java.util.HashMap;
import com.fanhehe.util.http.HttpUtil;
import com.fanhehe.util.result.IResult;
import com.fanhehe.user.dto.CaptchaCode;
import org.springframework.stereotype.Service;
import com.fanhehe.user.service.CaptchaService;
import org.springframework.beans.factory.annotation.Value;

@Service("Impl.CaptchaService")
public class CaptchaServiceImpl extends HttpUtil<CaptchaCode> implements CaptchaService {

    @Value("${com.fanhehe.module.message}")
    private String EMAIL_SERVICE;

    @Override
    public String getEndpoint() {
        return this.EMAIL_SERVICE;
    }

    @Override
    public IResult<CaptchaCode> sendEmailCaptcha(String email, String app, String orderId) {

        Map<String, String> params = new HashMap<>();

        params.put("app", app);
        params.put("email", email);
        params.put("orderId", orderId);

        return this.post("/api/message/captcha/email/send", params);
    }

    @Override
    public IResult<CaptchaCode> verifyEmailCaptcha(String email, String app, String captcha) {
        Map<String, String> params = new HashMap<>();

        params.put("app", app);
        params.put("email", email);
        params.put("captcha", captcha);

        return this.post("/api/message/captcha/email/verify", params);
    }
}
