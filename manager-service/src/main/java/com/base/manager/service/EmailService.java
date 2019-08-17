package com.base.manager.service;

import com.base.manager.bean.EmailMessageBean;

/**
 * @program: asiainfo-mico-service
 * @description: 收发邮件控制接口，
 * @author: dengqx
 * @create: 2018-12-17 14:18
 **/
public interface EmailService {
    public void sendEmail(EmailMessageBean emailMessageBean);
}
