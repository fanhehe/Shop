package com.fanhehe.user.config;

import java.lang.reflect.Method;
import org.springframework.context.annotation.Bean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.annotation.CachingConfigurerSupport;



@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public KeyGenerator keyGenerator() {

        return (Object o, Method method, Object... objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName());
            sb.append(method.getName());
            for (Object item: objects) {
                sb.append(item.toString());
            }
            return sb.toString();
        };

    }
}
