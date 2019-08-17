package com.base.manager.web;

import com.base.manager.bean.EmailMessageBean;
import com.base.manager.domain.UserBean;
import com.base.manager.service.EmailService;
import com.base.manager.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户管理控制
 * @author: dengqx
 * @createTime: 2019-03-10 13:06
 */
@Api(value = "用户管理控制",description = "用户管理控制")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public int addUser(UserBean userBean){
       return userService.addUser(userBean);
    }
    @ApiOperation(value = "发送邮件", notes = "发送邮件")
    @RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
    public void sendEmail(){
        EmailMessageBean emailMessageBean = new EmailMessageBean();
        emailMessageBean.setToUser("937610260@qq.com");
        emailMessageBean.setSubject("测试");
        emailMessageBean.setText("123456");
        emailService.sendEmail(emailMessageBean);
    }
}