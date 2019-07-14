package com.fanhehe.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append(method.getName());
                for (Object item: objects) {
                    sb.append(item.toString());
                }

                return sb.toString();
            }
        };
    }
}
