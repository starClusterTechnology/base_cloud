package com.base.manager.service;

import com.base.manager.domain.UserBean;

/**
 * @description: 用户管理业务处理接口
 * @author: dengqx
 * @createTime: 2019-03-10 13:14
 */
public interface UserService {
    int addUser(UserBean userBean);
}