package com.fanhehe.backend.controller.auth;

import com.fanhehe.backend.dto.Salt;
import com.fanhehe.util.result.IResult;
import com.fanhehe.util.result.InvokeResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaltController {

    @RequestMapping(path = "/api/auth/salt", method = RequestMethod.GET)
    public IResult<Salt> getSalt() {
        Salt salt = new Salt();
        salt.setSalt("");
        return InvokeResult.success(salt);
    }
}
