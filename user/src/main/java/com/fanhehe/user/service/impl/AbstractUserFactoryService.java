package com.fanhehe.user.service.impl;

import java.util.concurrent.locks.Lock;
import com.fanhehe.user.service.UidFactory;
import com.fanhehe.user.service.UserService;
import com.fanhehe.user.service.NickFactory;
import com.fanhehe.user.service.AvatarFactory;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

@Service("Abstract.UserFactoryService")
public abstract class AbstractUserFactoryService implements UidFactory, NickFactory, AvatarFactory {

    private final static int delta = 10000;
    private final double threshold = 0.8;
    private static final String REDIS_KEY_OF_UID_MAX = "REDIS_KEY_OF_UID_MAX";
    private static final String REDIS_KEY_OF_UID_SET = "REDIS_KEY_OF_UID_SET";

    private Lock lock = new ReentrantLock();

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

        if (member != null) {
            return Integer.valueOf(member);
        }

        increment();

        member = setOps.pop(REDIS_KEY_OF_UID_SET);

        if (member != null) {
            return Integer.valueOf(member);
        }

        return -1;
    }

    private boolean reachThreshold() {
        return stringRedisTemplate.opsForSet().size(REDIS_KEY_OF_UID_SET) / delta < 1 - threshold;
    }

    private int getMinRegisterUid() {
        int maxUid = userService.findMaxUid();
        String max = stringRedisTemplate.opsForValue().get(REDIS_KEY_OF_UID_MAX);
        return Math.max(maxUid + 1, Integer.valueOf(max == null ? "0" : max) + 1);
    }

    private void increment() {

        if (!reachThreshold()) {
            return;
        }

        try {
            lock.lock();

            if (!reachThreshold()) {
                return;
            }

            int min = getMinRegisterUid();

            for (int i = min; i < min + delta; i++) {
                stringRedisTemplate.opsForSet().add(REDIS_KEY_OF_UID_SET, String.valueOf(i));
            }

            stringRedisTemplate.opsForValue().increment(REDIS_KEY_OF_UID_MAX, delta);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
