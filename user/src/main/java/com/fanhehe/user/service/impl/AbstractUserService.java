package com.fanhehe.user.service.impl;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import com.fanhehe.user.dao.UserDao;
import com.fanhehe.user.model.User;
import com.fanhehe.user.constant.BindEnum;
import com.fanhehe.user.service.UserService;
import org.springframework.stereotype.Service;


@Service("abstract.userService")
public abstract class AbstractUserService implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserDao userDao;

    @Resource(name = "userDao")
    public void setUser(UserDao userDao) {
        this.userDao = userDao;
    }

    public int findMaxUid() {
        return userDao.findMaxUid();
    }

    public User findUserByUid(int uid) {
        return userDao.findUserByUid(uid);
    }

    public User createUserByEmail(String email, String password, HashMap<String, String> options) {
        return commonCreateUser(email, password, BindEnum.EMAIL, options);
    }
    public User createUserByPhone(String phone, String password, HashMap<String, String> options) {
        return commonCreateUser(phone, password, BindEnum.PHONE, options);
    }
}