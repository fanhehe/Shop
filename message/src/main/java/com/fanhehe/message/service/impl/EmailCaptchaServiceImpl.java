package com.fanhehe.message.service.impl;

import javax.validation.constraints.NotNull;
import com.fanhehe.message.dao.CaptchaCodeDao;
import com.fanhehe.message.util.*;
import com.fanhehe.message.constant.BindEnum;
import com.fanhehe.message.constant.CaptchaStatus;
import com.fanhehe.message.constant.ReceiverType;
import com.fanhehe.message.dto.Receiver;
import com.fanhehe.message.dto.bind.Bind;
import com.fanhehe.message.model.CaptchaCode;
import com.fanhehe.message.model.Message;
import com.fanhehe.message.service.BindService;
import com.fanhehe.message.service.CaptchaService;
import com.fanhehe.message.service.EmailMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service("Impl.EmailCaptchaService")
public class EmailCaptchaServiceImpl implements CaptchaService<CaptchaCode> {

    private BindService bindService;
    private CaptchaCodeDao captchaCodeDao;
    private EmailMessageService emailMessageService;

    private Logger logger = LoggerFactory.getLogger(EmailCaptchaServiceImpl.class);

    @Autowired
    public void setBindService(BindService bindService) {
        this.bindService = bindService;
    }

    @Autowired
    public void setCaptchaCodeModelService(CaptchaCodeDao captchaCodeDao) {
        this.captchaCodeDao = captchaCodeDao;
    }

    @Autowired
    public void setEmailMessageService(EmailMessageService emailMessageService) {
        this.emailMessageService = emailMessageService;
    }

    @Override
    public IResult<CaptchaCode> sent(CaptchaCode captchaCode) {

        int timestamp = Time.makeUnixTimestamp();

        CaptchaCode overtime = captchaCodeDao.selectLatest(captchaCode.getApp(), captchaCode.getTarget());

        if (overtime != null && overtime.getOvertime() + overtime.getCreatedAt() > timestamp) {
            return InvokeResult.failure("需要等待" + overtime.getOvertime() + "秒再发");
        }

        CaptchaCode result = captchaCodeDao.selectByAppAndOrderId(captchaCode.getApp(), captchaCode.getOrderId());

        if (result == null || result.getStatus() == CaptchaStatus.FAILURE.getValue()) {
            return InvokeResult.success();
        }

        return InvokeResult.failure("存在记录");
    }

    @Override
    @NotNull
    public IResult<CaptchaCode> send(Receiver receiver, CaptchaCode captchaCode) {

        IResult<CaptchaCode> sent = sent(captchaCode);

        if (sent.isFailure()) {
            return sent;
        }

        String target = receiver.getTarget();
        int instant = Time.makeUnixTimestamp();

        if (receiver.getReceiverType() == ReceiverType.UID) {
            IResult<Bind> bindIResult = bindService.getBindByUid(Integer.parseInt(receiver.getTarget()), BindEnum.EMAIL);

            if (bindIResult.isSuccess() && bindIResult.getData() != null) {
                target = bindIResult.getData().getOpenid();
            } else if (bindIResult.isSuccess()) {
                return InvokeResult.failure("用户未绑定邮箱");
            } else {
                return InvokeResult.failure("未知异常");
            }
        }

        if (!Regexp.isEmail(target)) {
            return InvokeResult.failure("非法邮箱");
        }

        // 60s 过期时间
        captchaCode.setOvertime(60);
        captchaCode.setCreatedAt(instant);
        captchaCode.setUpdatedAt(instant);
        captchaCode.setCode(RandomCode.rand());
        captchaCode.setStatus(CaptchaStatus.SENDING.getValue());

        captchaCodeDao.insertInto(
                captchaCode.getUid(),
                captchaCode.getTarget(),
                captchaCode.getApp(),
                captchaCode.getOrderId(),
                captchaCode.getOvertime(),
                captchaCode.getCode(),
                captchaCode.getStatus(),
                captchaCode.getCreatedAt(),
                captchaCode.getUpdatedAt());

        IResult<Message> result = emailMessageService.sendEmailMessageByEmail(target, "邮箱验证码", captchaCode.getCode(), captchaCode.getApp(), captchaCode.getOrderId());

        if (result.isSuccess()) {
            captchaCodeDao.updateStatusByAppAndOrderId(
                    captchaCode.getApp(),
                    captchaCode.getOrderId(),
                    CaptchaStatus.SUCCESS.getValue()
            );
            return InvokeResult.success(captchaCode);
        }

        captchaCodeDao.updateStatusByAppAndOrderId(
                captchaCode.getApp(),
                captchaCode.getOrderId(),
                CaptchaStatus.FAILURE.getValue()
        );

        return InvokeResult.failure("发送消息失败");
    }

    @Override
    @NotNull
    public IResult<CaptchaCode> verify(Receiver receiver, CaptchaCode captchaCode) {

        CaptchaCode result = captchaCodeDao.selectLatest(captchaCode.getApp(), receiver.getTarget());

        if (result != null && result.getCode().equals(captchaCode.getCode())) {
            return InvokeResult.success(result);
        }

        return InvokeResult.failure("验证码错误");
    }
}
