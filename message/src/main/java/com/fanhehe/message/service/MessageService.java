package com.fanhehe.message.service;

import java.util.HashMap;
import com.fanhehe.message.util.IResult;
import com.fanhehe.message.dto.Receiver;
import com.fanhehe.message.model.Message;
import com.fanhehe.message.dto.BaseMessage;
import com.fanhehe.message.constant.ReceiverType;
import com.fanhehe.message.constant.MessageCategory;
import javax.validation.constraints.NotNull;

public interface MessageService<T extends BaseMessage> {
    /**
     * 支持通过收件人，消息，属性，三个维度发消息
     * @param receiver 收信人
     * @param message 消息实体
     * @param property 属性
     * @return 返回是否正确的Message
     */
    IResult<Message> sendMessage(
            Receiver receiver,
            T message,
            HashMap<String, String> property
    );

    /**
     * 支持多参数形式的发消息
     * @param receiver 接收方
     * @param receiverType 接收方类型
     * @param messageCategory 消息类型
     * @param content 消息内容
     * @param orderId 订单ID
     * @param property 属性
     * @return 调用结果的Message
     */
    IResult<Message> sendMessage(
            String receiver,
            ReceiverType receiverType,
            MessageCategory messageCategory,
            String content,
            String app,
            String orderId,
            HashMap<String, String> property
    );

    default HashMap<String, String> makeMessageProperty() {
        return makeMessageProperty(null);
    }

    /**
     * 根据传进来的proto生成新的HashMap
     * @param proto 属性原型
     * @return 新的属性
     */
    @NotNull
    default HashMap<String, String> makeMessageProperty(HashMap<String, String> proto) {

        if (proto == null) {
            return new HashMap<>();
        }

        return new HashMap<>(proto);
    }
}
