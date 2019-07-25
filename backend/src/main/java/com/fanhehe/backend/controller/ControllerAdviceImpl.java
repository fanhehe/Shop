package com.fanhehe.backend.controller;

import com.fanhehe.util.result.IResult;
import com.fanhehe.util.result.InvokeResult;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class ControllerAdviceImpl {

    private static Logger logger = LoggerFactory.getLogger(ControllerAdviceImpl.class);

    @ResponseBody
    @ExceptionHandler
    public IResult defaultExceptionHandler(HttpServletRequest request, Exception e) {

        logger.warn(e.getMessage());
        logger.warn(request.toString());

        return InvokeResult.failure("内部错误 from defaultExceptionHandler", 5000);
    }

    @ResponseBody
    @ExceptionHandler
    public IResult defaultExceptionHandler(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        String method = request.getMethod();
        String path = request.getRequestURI();
        String supported = e.getSupportedHttpMethods().toString();

        logger.info(e.getMessage());
        logger.warn(request.toString());

        return InvokeResult.failure(path + " is not support visit by " + method + ". please replace it with " + supported + ".", 405);
    }

    @ResponseBody
    @ExceptionHandler
    public IResult defaultExceptionHandler(HttpServletRequest request, NoHandlerFoundException e) {

        logger.info(e.getMessage());
        logger.warn(request.toString());

        return InvokeResult.failure(e.getRequestURL() + " doesn't exists", 404);
    }

}
