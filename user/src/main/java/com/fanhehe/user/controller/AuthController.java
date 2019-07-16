package com.fanhehe.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fanhehe.user.model.User;
import com.fanhehe.util.result.IResult;
import javax.annotation.security.PermitAll;
import com.fanhehe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    @Qualifier("Impl.UserService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PermitAll
    @RequestMapping(value = "/api/auth", method = RequestMethod.GET)
    public IResult<User> login(@RequestParam(defaultValue = "0") int uid) {
        return null;
    }
}
