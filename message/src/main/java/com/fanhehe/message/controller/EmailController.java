package com.fanhehe.message.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fanhehe.util.result.IResult;
import com.fanhehe.message.model.Message;
import com.fanhehe.message.service.EmailMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

	private EmailMessageService emailMessageService;

	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	@Qualifier("Impl.EmailMessageService")
	public void setEmailMessageService(EmailMessageService emailMessageService) {
		this.emailMessageService = emailMessageService;
	}

	@RequestMapping(value = "/api/message/send-email", method = RequestMethod.GET)
	public IResult<Message> sendEmail(
			@RequestParam(defaultValue = "") String app,
			@RequestParam(defaultValue = "") String email,
			@RequestParam(defaultValue = "") String orderId
			) {
		return emailMessageService.sendEmailMessageByEmail(email, "测试邮件", "内容邮件",app, orderId);
	}
}