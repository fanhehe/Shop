package com.fanhehe.message.dao;

import com.fanhehe.message.model.CaptchaCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CaptchaCodeDao extends BaseDao<CaptchaCode> {}
