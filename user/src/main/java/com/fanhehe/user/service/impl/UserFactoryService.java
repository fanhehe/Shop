package com.fanhehe.user.service.impl;

import org.springframework.stereotype.Service;

@Service("userFactory")
public class UserFactoryService extends AbstractUserFactoryService {

    public int createUid() {
        return super.createUid();
    }

    public String createNick() {
        return "rand nick";
    }

    public String createAvatar() {
        return "rand avatar";
    }
}
