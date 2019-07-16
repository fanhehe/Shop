package com.fanhehe.message.service.impl;

import java.util.HashMap;
import com.fanhehe.message.util.Time;
import com.fanhehe.util.result.IResult;
import com.fanhehe.util.result.InvokeResult;
import com.fanhehe.message.constant.MessageCategory;
import com.fanhehe.message.constant.MessageStatus;
import com.fanhehe.message.constant.ReceiverType;
import com.fanhehe.message.dao.MessageDao;
import com.fanhehe.message.dto.EmailMessage;
import com.fanhehe.message.dto.Receiver;
import com.fanhehe.message.model.Message;
import com.fanhehe.message.service.EmailMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service("Impl.EmailMessageService")
public class EmailMessageServiceImpl implements EmailMessageService {

    private MessageDao messageDao;
    private JavaMailSender mailSender;

    @Autowired
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 支持通过收件人，消息，属性，三个维度发消息
     * @param receiver 收信人
     * @param message 消息实体
     * @param property 属性
     * @return 返回是否正确的Message
     */
    public IResult<Message> sendMessage(
            Receiver receiver,
            EmailMessage message,
            HashMap<String, String> property
    ) {

        Integer uid = 0;
        String app = message.getApp();
        String target = receiver.getTarget();
        String orderId = message.getOrderId();

        switch(receiver.getReceiverType()) {
            case UID:
                uid = Integer.valueOf(target);
                target = "13889441200@163.com";
                break;
            case EMAIL:
                break;
            default:
                break;
        }

        int current = Time.makeUnixTimestamp();

        Message record = messageDao.findMessageByAppIdAndOrderId(message.getApp(), message.getOrderId());

        if (record != null) {
            int status = record.getStatus();

            if (status == MessageStatus.INIT.getValue()) {
                return InvokeResult.failure("消息正在处理中");
            }

            if (status == MessageStatus.SUCCESS.getValue()) {
                return InvokeResult.failure("消息已发成功");
            }
        }

        if (record == null) {
            messageDao.createMessage(
                    app,
                    message.getCategory().getValue(),
                    uid,
                    target,
                    receiver.getReceiverType().getValue(),
                    MessageStatus.INIT.getValue(),
                    message.getTitle(),
                    message.getContent(),
                    "",
                    "",
                    orderId,
                    message.getDuration(),
                    current,
                    current);
            record = messageDao.findMessageByAppIdAndOrderId(message.getApp(), message.getOrderId());
        }

        int rows = messageDao.
                updateMessageStatusByAppAndOrderIdAndStatus(
                        app,
                        orderId,
                        MessageStatus.INIT.getValue(),
                        MessageStatus.SENDING.getValue());

        if (rows != 1) {
            return InvokeResult.failure("更新行数不为1");
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("testfanhehe@163.com");
        simpleMailMessage.setTo(target);
        simpleMailMessage.setSubject(message.getTitle());
        simpleMailMessage.setText(message.getContent());

        MessageStatus status = MessageStatus.FAILURE;

        try {

            mailSender.send(simpleMailMessage);
            status = MessageStatus.SUCCESS;
        } catch (MailException e) {
            e.printStackTrace();
            return InvokeResult.failure(e.getMessage());
        } finally {
            try {
                messageDao.updateMessageStatusByAppAndOrderId(app, orderId, status.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return InvokeResult.success(messageDao.findMessageByAppIdAndOrderId(message.getApp(), message.getOrderId()));
    }

    /**
     * 邮件属于负载类型的消息，不支持单content类型的消息
     * @param receiver 接收方
     * @param receiverType 接收方类型
     * @param messageCategory 消息类型
     * @param content 消息内容
     * @param orderId 订单ID
     * @param property 属性
     * @return 调用结果的Message
     */
    public IResult<Message> sendMessage(
            String receiver,
            ReceiverType receiverType,
            MessageCategory messageCategory,
            String content,
            String app,
            String orderId,
            HashMap<String, String> property
    ) {
        return InvokeResult.failure("邮箱不支持本方法");
    }
}
