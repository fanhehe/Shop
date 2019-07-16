package com.fanhehe.user.service.impl;

import org.slf4j.Logger;
import java.util.HashMap;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import com.fanhehe.user.model.User;
import com.fanhehe.user.dao.UserDao;
import com.fanhehe.user.util.Time;
import com.fanhehe.user.util.Crypto;
import com.fanhehe.util.result.IResult;
import com.fanhehe.user.constant.BindEnum;
import com.fanhehe.util.result.InvokeResult;
import com.fanhehe.user.service.UserService;
import com.fanhehe.user.service.BindService;
import com.fanhehe.user.service.HandleUserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


@Service("Impl.UserService")
public class UserServiceImpl extends AbstractUserService implements HandleUserService<User> {

    private UserDao userDao;

    private BindService bindService;

    private UserFactoryService userFactoryService;
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public void setUserDao(UserDao userDao) { this.userDao = userDao; }

    @Autowired
    @Qualifier("Impl.BindService")
    public void setBindService(BindService bindService) {
        this.bindService = bindService;
    }

    @Autowired
    @Qualifier("userFactory")
    public void setUserFactoryService(UserFactoryService userFactoryService) {
        this.userFactoryService = userFactoryService;
    }

    public boolean checkBindUsed(String target, BindEnum bindEnum) {
        return bindService.existBindByOpenidAndType(target, bindEnum.getValue());
    }
    public IResult<User> handleMakeNewUser(String target, String password, BindEnum bindType, HashMap<String, String> options)  {

        User user;
        String credential = Optional.ofNullable(options.get("credential")).orElse("");

        if (checkBindUsed(target, bindType)) {
            logger.info(target + "的第三方账号已被使用, 不能注册");
            return InvokeResult.failure("第三方绑定信息已被使用");
        }

        switch(bindType) {
            case PHONE:
                user = createUserByPhone(target, password);
                break;
            case EMAIL:
                user = createUserByEmail(target, password);
                break;
            default:
                return null;
        }

        bindService.handleCreateBind(user.getUid(), bindType, target, credential);

        return InvokeResult.success(user);
    }

    public User commonCreateUser(String target, String password, BindEnum bindType, HashMap<String, String> options) {

        String saltFromDB = "";
        String saltFromRedis = "";

        int instant = Time.makeUnixTimestamp();
        int uid = userFactoryService.createUid();
        String nick = userFactoryService.createNick();
        String avatar = userFactoryService.createAvatar();

        String hashedPassword = Crypto.makePassword(password, saltFromDB, saltFromRedis);

        userDao.createUser(uid, nick, avatar, saltFromDB, hashedPassword, instant, instant);

        return findUserByUid(uid);
    }
}
