package com.fanhehe.user.service.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Created by fanhehe on 11/07/2019.
 *
 */
@Aspect
public class AbstractUserFactoryAspect {

    private static Logger logger = LoggerFactory.getLogger(AbstractUserFactoryAspect.class);

    @Pointcut("execution(* com.fanhehe.user.service.impl.AbstractUserFactoryService.createUid(..))")
    public void createUid() {

    }

    @Before("createUid()")
    public void beforeCreateUId() {
        logger.info("before");
    }

    @After("createUid()")
    public void afterCreateUidToIncr() {
        logger.info("after");
    }
}
