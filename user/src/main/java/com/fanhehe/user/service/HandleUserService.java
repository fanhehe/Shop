package com.fanhehe.user.service;

import java.util.HashMap;
import com.fanhehe.user.constant.BindEnum;
import com.fanhehe.user.util.IResult;

public interface HandleUserService<T> {

    boolean checkBindUsed(String target, BindEnum bindEnum);

    IResult<T> handleMakeNewUser(String target, String password, BindEnum bindEnum, HashMap<String, String> options);
}
