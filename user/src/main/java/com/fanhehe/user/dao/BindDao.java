package com.fanhehe.user.dao;

import com.fanhehe.user.model.Bind;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BindDao {

    @Select("select id from bind where openid = #{openid} and type = #{type} limit 1")
    Integer existBindByOpenidAndType(
            @Param("openid") String openid,
            @Param("type") int type);

    @Select("select * from bind where uid = #{uid}")
    Bind findBindByUid(int uid);

    @Select("select * from bind where uid = #{uid} and type = #{type}")
    Bind findBindByUidAndType(int uid, int type);

    @Insert("insert into bind (uid, type, openid, credential, createdAt, updatedAt) values (#{uid}, #{type}, #{openid}, #{credential}, #{createdAt}, #{updatedAt})")
    void createBind(
            @Param("uid") int uid,
            @Param("type") int type,
            @Param("openid") String openid,
            @Param("credential") String credential,
            @Param("createdAt") int createdAt,
            @Param("updatedAt") int updatedAt);
}
