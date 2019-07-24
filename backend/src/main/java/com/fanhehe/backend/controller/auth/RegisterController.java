package com.fanhehe.backend.controller.auth;

import com.fanhehe.util.result.IResult;
import com.fanhehe.util.result.InvokeResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RegisterController {
    @RequestMapping(path = "/api/auth/register/", method = RequestMethod.POST)
    public IResult register(
        @RequestParam(name = "target") String target,
        @RequestParam(name = "password") String password
    ) {
        return InvokeResult.failure("xxxx");
    }
}
