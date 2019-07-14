package com.fanhehe.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.security.PermitAll;
import com.fanhehe.user.service.UserService;
import com.fanhehe.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class LoginController {

	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	@Qualifier("Impl.UserService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@PermitAll
	@RequestMapping(value = "/api/user/login", method = RequestMethod.GET)
	public User login(@RequestParam(defaultValue = "0") int uid) {

		if (uid <= 0) {
			return null;
		}

		logger.info("有用户申请登录: " + uid);

		User user = userService.findUserByUid(uid);

		if (user == null) {
			logger.warn("用户信息不存在");
		}

		return user;
	}
}