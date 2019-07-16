package com.fanhehe.user.service;

import java.util.HashMap;
import com.fanhehe.util.result.IResult;
import com.fanhehe.user.constant.BindEnum;

public interface HandleUserService<T> {

    boolean checkBindUsed(String target, BindEnum bindEnum);

    IResult<T> handleMakeNewUser(String target, String password, BindEnum bindEnum, HashMap<String, String> options);

    default IResult<T> handleMakeNewUser(String target, String password, BindEnum bindENum) {
        return handleMakeNewUser(target, password, bindENum, new HashMap<>());
    }
}
