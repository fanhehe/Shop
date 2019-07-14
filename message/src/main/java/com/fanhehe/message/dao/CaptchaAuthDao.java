package com.fanhehe.message.dao;

import org.apache.ibatis.annotations.Mapper;
import com.fanhehe.message.model.CaptchaAuth;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface CaptchaAuthDao extends BaseDao<CaptchaAuth>{
}
