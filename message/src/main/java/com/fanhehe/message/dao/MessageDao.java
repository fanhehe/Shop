package com.fanhehe.message.dao;

import com.fanhehe.message.model.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MessageDao {

    @Select("select * from message where id = #{id}")
    Message findMessageById(@Param("id") int id);

    @Select("select * from message where uid = #{uid} limit = #{limit}")
    Message[] findMessageByUidAndLimit(@Param("uid") int uid, int limit);

    @Select("select * from message where app = #{app} and orderId = #{orderId} limit 1")
    Message findMessageByAppIdAndOrderId(@Param("app") String app, @Param("orderId") String orderId);

    @Update("update message set status = #{status} where app = #{app} and orderId = #{orderId}")
    int updateMessageStatusByAppAndOrderId(
            @Param("app") String app,
            @Param("orderId") String orderId,
            @Param("status") int status
    );

    @Update("update message set status = #{newStatus} where app = #{app} and orderId = #{orderId} and status=#{oldStatus}")
    int updateMessageStatusByAppAndOrderIdAndStatus(
            @Param("app") String app,
            @Param("orderId") String orderId,
            @Param("oldStatus") int oldStatus,
            @Param("newStatus") int newStatus
    );

    @Insert("insert into message (app, category, uid, receiver, receiverType, status, title, content, messagePayload, attachments, orderId, duration, createdAt, updatedAt) values (#{app}, #{category}, #{uid}, #{receiver}, #{receiverType}, #{status}, #{title}, #{content}, #{messagePayload}, #{attachments}, #{orderId}, #{duration}, #{createdAt}, #{updatedAt})")
    void createMessage(
            @Param("app") String app,
            @Param("category") int category,
            @Param("uid") Integer uid,
            @Param("receiver") String receiver,
            @Param("receiverType") int receiverType,
            @Param("status") int status,
            @Param("title") String title,
            @Param("content") String content,
            @Param("messagePayload") String messagePayload,
            @Param("attachments") String attachments,
            @Param("orderId") String orderId,
            @Param("duration") int duration,
            @Param("createdAt") int createdAt,
            @Param("updatedAt") int updatedAt
    );
}
