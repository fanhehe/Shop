package com.fanhehe.user.util;

import org.springframework.stereotype.Component;

@Component("Abstract.Result")
public abstract class AbstractResult<T> implements IResult<T> {

    private int code = 0;
    private T data = null;
    private String message = "";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return code == 0;
    }
}

