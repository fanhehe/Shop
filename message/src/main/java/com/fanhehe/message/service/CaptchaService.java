package com.fanhehe.message.service;

import com.fanhehe.util.result.IResult;
import com.fanhehe.message.dto.Receiver;

public interface CaptchaService<T> {

    /**
     * 判断是否发送过
     * @param e 信息本体
     * @return 是否发送过
     */
    IResult<T> sent(T e);

    IResult<T> send(Receiver receiver, T e);

    IResult<T> verify(Receiver receiver, T e);

}
