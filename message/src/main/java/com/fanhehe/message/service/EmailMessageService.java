package com.fanhehe.message.service;

import java.util.HashMap;
import com.fanhehe.message.dto.Receiver;
import com.fanhehe.message.model.Message;
import com.fanhehe.message.dto.EmailMessage;
import com.fanhehe.message.constant.ReceiverType;
import com.fanhehe.message.constant.MessageCategory;
import com.fanhehe.util.result.IResult;

public interface EmailMessageService extends MessageService<EmailMessage> {

    default IResult<Message> sendEmailMessageByUid(
            int uid,
            String title,
            String content,
            String app,
            String orderId,
            String[] cc,
            String[] bcc,
            String[] attachments,
            HashMap<String, String> property
            ) {
        return sendEmailMessage(
                String.valueOf(uid),
                ReceiverType.UID,
                title,
                content,
                app,
                orderId,
                cc,
                bcc,
                attachments,
                property
        );
    }

    default IResult<Message> sendEmailMessageByEmail(
            String email,
            String title,
            String content,
            String app,
            String orderId
    ) {
        return sendEmailMessageByEmail(email, title, content, app, orderId, new String[0], new String[0], new String[0], null);
    }

    default IResult<Message> sendEmailMessageByEmail(
            String email,
            String title,
            String content,
            String app,
            String orderId,
            String[] cc,
            String[] bcc,
            String[] attachments,
            HashMap<String, String> property
            ) {
        return sendEmailMessage(email, ReceiverType.EMAIL, title, content, app, orderId, cc, bcc, attachments, property);
    }

    default IResult<Message> sendEmailMessage(
            String target,
            ReceiverType receiverType,
            String title,
            String content,
            String app,
            String orderId,
            String[] cc,
            String[] bcc,
            String[] attachments,
            HashMap<String, String> property
    ) {
        Receiver receiver = new Receiver();
        receiver.setTarget(target);
        receiver.setReceiverType(receiverType);

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setCc(cc);
        emailMessage.setBcc(bcc);
        emailMessage.setApp(app);
        emailMessage.setTitle(title);
        emailMessage.setContent(content);
        emailMessage.setOrderId(orderId);
        emailMessage.setAttachments(attachments);
        emailMessage.setCategory(MessageCategory.EMAIL);

        return sendMessage(receiver, emailMessage, property);
    }
}
