package com.fanhehe.message.util;

import org.springframework.stereotype.Component;

@Component("JsonResult")
public class JsonResult<T> extends AbstractResult<T> {

    public static <T> IResult<T> success(T data) {
        IResult<T> result = new InvokeResult<>();
        result.setData(data);
        return result;
    }

    public static <T> IResult<T> failure(String message) {
        return failure(message, 500, null);
    }

    public static <T> IResult<T> failure(String message, int code) {
        return failure(message, code, null);
    }

    public static<T> IResult<T> failure(String message, int code, T data) {
        IResult<T> result = new JsonResult<>();
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);
        return result;
    }
}
