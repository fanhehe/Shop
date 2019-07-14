package com.fanhehe.message.service.impl;

import com.fanhehe.message.constant.CaptchaStatus;
import com.fanhehe.message.dao.BaseDao;
import com.fanhehe.message.dto.Receiver;
import com.fanhehe.message.model.CaptchaAuth;
import com.fanhehe.message.model.CaptchaCode;
import com.fanhehe.message.service.CaptchaService;
import com.fanhehe.message.util.IResult;
import com.fanhehe.message.util.InvokeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;


@Service("Impl.EmailCaptchaService")
public class EmailCaptchaServiceImpl implements CaptchaService<CaptchaCode> {

    private BaseDao<CaptchaAuth> captchaAuthDao;
    private BaseDao<CaptchaCode> captchaCodeDao;


    @Autowired
    public void setCaptchaAuthModelService(BaseDao<CaptchaAuth> captchaAuthDao) {
        this.captchaAuthDao = captchaAuthDao;
    }

    @Autowired
    public void setCaptchaCodeModelService(BaseDao<CaptchaCode> captchaCodeDao) {
        this.captchaCodeDao = captchaCodeDao;
    }

    @Override
    public IResult<CaptchaCode> sent(CaptchaCode captchaCode) {
        CaptchaCode cc = new CaptchaCode();

        cc.setApp(captchaCode.getApp());
        cc.setOrderId(captchaCode.getOrderId());

        CaptchaCode result = captchaCodeDao.selectOne(cc);

        if (result == null || result.getStatus() == CaptchaStatus.FAILURE.getValue()) {
            return InvokeResult.success();
        }

        return InvokeResult.failure("存在记录");
    }

    @Override
    @NotNull
    public IResult<CaptchaCode> send(Receiver receiver, CaptchaCode captchaCode) {
        String target = receiver.getTarget();
        return null;
    }

    @Override
    @NotNull
    public IResult<CaptchaCode> verify(Receiver receiver, CaptchaCode captchaCode) {
        return null;
    }
}
