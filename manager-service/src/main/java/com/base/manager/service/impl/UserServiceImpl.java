package com.base.manager.service.impl;

import com.base.manager.dao.UserBeanMapper;
import com.base.manager.domain.UserBean;
import com.base.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户管理业务处理实现类
 * @author: dengqx
 * @createTime: 2019-03-10 13:15
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserBeanMapper userBeanMapper;

    @Override
    public int addUser(UserBean userBean) {
        return userBeanMapper.insert(userBean);
    }

}