package com.fanhehe.user.dao;

import com.fanhehe.user.model.Bind;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BindHistoryDao extends BindDao {

    @Select("select id from bind_history where openid = #{openid} and type = #{type} limit 1")
    Integer existBindByOpenidAndType(
            @Param("openid") String openid,
            @Param("type") int type);

    @Select("select * from bind_history where uid = #{uid}")
    Bind findBindByUid(int uid);

    @Select("select * from bind_history where uid = #{uid} and type = #{type}")
    Bind findBindByUidAndType(int uid, int type);

    @Insert("insert into bind_history (uid, type, openid, credential, createdAt, updatedAt) values (#{uid}, #{type}, #{openid}, #{credential}, #{createdAt}, #{updatedAt})")
    void createBind(
            @Param("uid") int uid,
            @Param("type") int type,
            @Param("openid") String openid,
            @Param("credential") String credential,
            @Param("createdAt") int createdAt,
            @Param("updatedAt") int updatedAt);
}
