package com.fanyao.alibaba.usercenter.service;

import com.fanyao.alibaba.usercenter.dao.user.UserMapper;
import com.fanyao.alibaba.usercenter.entity.po.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: bugProvider
 * @date: 2019/12/20 14:07
 * @description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserMapper userMapper;

    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
