package com.base.manager.bean;

import java.io.Serializable;

/**
 * @program: asiainfo-mico-service
 * @description: 接发邮件实体类.
 * @author: dengqx
 * @create: 2018-12-19 15:55
 **/
public class EmailMessageBean implements Serializable {
    private String toUser;
    private String subject;
    private String text;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
