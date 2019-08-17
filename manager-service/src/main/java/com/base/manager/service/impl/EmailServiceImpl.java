package com.base.manager.service.impl;

import com.base.manager.bean.EmailMessageBean;
import com.base.manager.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @program: asiainfo-mico-service
 * @description: 收发邮件业务实现
 * @author: dengqx
 * @create: 2018-12-17 14:18
 **/
@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Value("${spring.mail.username}")
    private String fromUser;
    @Autowired
    JavaMailSender mailSender;//spring提供的发送类。

    @Override
    public void sendEmail(EmailMessageBean emailMessageBean) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromUser);//发送者
        mailMessage.setTo(emailMessageBean.getToUser());//接收者
        mailMessage.setSubject(emailMessageBean.getSubject());//标题
        mailMessage.setText(emailMessageBean.getText());//内容
        try {
            mailSender.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
