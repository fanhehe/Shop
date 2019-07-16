package com.fanhehe.user.service.impl;


import com.fanhehe.user.service.UidFactory;
import com.fanhehe.user.service.UserService;
import com.fanhehe.user.service.NickFactory;
import com.fanhehe.user.service.AvatarFactory;

import com.fanhehe.user.util.Console;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;

@Service("Abstract.UserFactoryService")
public abstract class AbstractUserFactoryService implements UidFactory, NickFactory, AvatarFactory {

    private final double threshold = 0.8;
    private final static int delta = 10000;
    private static final String REDIS_KEY_OF_UID_MAX = "REDIS_KEY_OF_UID_MAX";
    private static final String REDIS_KEY_OF_UID_SET = "REDIS_KEY_OF_UID_SET";

    private volatile AtomicBoolean growing = new AtomicBoolean(false);

    private UserService userService;
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("Impl.UserService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public int createUid() {

        SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();

        String member = setOps.pop(REDIS_KEY_OF_UID_SET);

        Console.log("=======================");

        if (reachThreshold()) {
            increment();
        }

        if (member == null) {
            member = setOps.pop(REDIS_KEY_OF_UID_SET);
        }

        return Integer.valueOf(member);
    }

    private boolean reachThreshold() {
        return stringRedisTemplate
                .opsForSet()
                .size(REDIS_KEY_OF_UID_SET) / delta < (1 - threshold);
    }

    private int getMinRegisterUid() {

        String max = stringRedisTemplate
                .opsForValue().get(REDIS_KEY_OF_UID_MAX);

        int maxUid = userService.findMaxUid();
        int maxRedis = Integer.valueOf(max == null ? "0" : max);

        int result = Math.max(maxUid, maxRedis) + 1;

        if (max == null || maxUid > maxRedis) {
            stringRedisTemplate.opsForValue().set(REDIS_KEY_OF_UID_MAX, String.valueOf(result));
        }

        return result;
    }

    private void increment() {

        if (!reachThreshold()) {
            return;
        }

        boolean grow = growing.compareAndSet(false, true);

        if (!grow) {
            return;
        }

        try {
            if (!reachThreshold()) {
                return;
            }

            int min = getMinRegisterUid();

            String[] addList = new String[delta];

            for (int i = min; i < min + delta; i++) {
                addList[i - min] = String.valueOf(i);
            }

            stringRedisTemplate.opsForSet().add(REDIS_KEY_OF_UID_SET, addList);
            stringRedisTemplate.opsForValue().increment(REDIS_KEY_OF_UID_MAX, delta);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            growing.set(false);
        }
    }
}
