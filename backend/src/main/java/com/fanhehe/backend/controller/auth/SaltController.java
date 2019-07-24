package com.fanhehe.backend.controller.auth;

import com.fanhehe.util.result.IResult;
import com.fanhehe.util.result.InvokeResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaltController {
    @RequestMapping(path = "/api/auth/", method = RequestMethod.GET)
    public IResult getSalt() {
        return InvokeResult.failure("sssssss");
    }
}
