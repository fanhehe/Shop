package com.fanhehe.message.dao;

import com.fanhehe.message.model.CaptchaCode;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CaptchaCodeDao extends BaseDao<CaptchaCode> {
    @Select("select id, code, overtime, created_at from captcha_code where app = #{app} and target = #{target} order by id desc limit 1")
    CaptchaCode selectLatest(@Param("app") String app, @Param("target") String target);

    @Select("select * from captcha_code where app = #{app} and order_id = #{orderId} limit 1")
    CaptchaCode selectByAppAndOrderId(
            @Param("app") String app,
            @Param("orderId") String orderId
            );

    @Update("update captcha_code set status = #{status} where app = #{app} and order_id = #{orderId}")
    int updateStatusByAppAndOrderId(
            @Param("app") String app,
            @Param("orderId") String orderId,
            @Param("status") int status);

    @Insert("insert into captcha_code (uid, target, app, order_id, overtime, code, status, created_at, updated_at) values (#{uid}, #{target}, #{app}, #{order_id}, #{overtime}, #{code}, #{status}, #{created_at}, #{updated_at})")
    void insertInto(
            @Param("uid") int uid,
            @Param("target") String target,
            @Param("app") String app,
            @Param("order_id") String orderId,
            @Param("overtime")int overtime,
            @Param("code") String code,
            @Param("status") int status,
            @Param("created_at") int createdAt,
            @Param("updated_at") int updatedAt);

}
