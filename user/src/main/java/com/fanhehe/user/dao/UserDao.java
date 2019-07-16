package com.fanhehe.user.dao;

import com.fanhehe.user.model.User;

import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao {

    @Select("select * from user where uid = #{uid}")
    User findUserByUid(int uid);

    @Select("select uid from user order by uid desc limit 1")
    Integer findMaxUid();

    @SelectKey(statement = "select last_insert_id()", keyProperty = "uid", before = false, resultType = Integer.class)
    @Insert("insert into user (uid, nick, avatar, salt, password, createdAt, updatedAt) values (#{uid}, #{nick}, #{avatar}, #{salt}, #{password}, #{createdAt}, #{updatedAt})")
    Integer createUser(
            @Param("uid") int uid,
            @Param("nick") String nick,
            @Param("avatar") String avatar,
            @Param("salt") String salt,
            @Param("password") String password,
            @Param("createdAt") int createdAt,
            @Param("updatedAt") int updatedAt);
}
