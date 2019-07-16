package com.fanhehe.message.service;

import com.fanhehe.util.result.IResult;
import com.fanhehe.message.dto.bind.Bind;
import com.fanhehe.message.constant.BindEnum;

public interface BindService {

    IResult<Bind> getBind(String target, BindEnum bindEnum);

    IResult<Bind> getBindByUid(int uid, BindEnum bindEnum);

    default IResult<Bind> getBindByEmail(String email) {
        return getBind(email, BindEnum.EMAIL);
    }

    default IResult<Bind>  getBindByPhone(String phone) {
        return getBind(phone, BindEnum.PHONE);
    }
}
