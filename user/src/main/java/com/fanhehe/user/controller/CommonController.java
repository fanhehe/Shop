package com.fanhehe.user.controller;

import com.fanhehe.util.result.IResult;
import com.fanhehe.util.result.InvokeResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
    @RequestMapping(path = "/404", method = RequestMethod.GET)
    public IResult action404() {
        return InvokeResult.failure("资源不存在", 400);
    }

    @RequestMapping(path = "/500", method = RequestMethod.GET)
    public IResult action500() {
        return InvokeResult.failure("内部异常", 500);
    }
}
