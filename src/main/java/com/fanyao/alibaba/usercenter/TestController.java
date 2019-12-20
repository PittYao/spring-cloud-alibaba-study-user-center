package com.fanyao.alibaba.usercenter;
import java.util.Date;

import com.fanyao.alibaba.usercenter.dao.user.UserMapper;
import com.fanyao.alibaba.usercenter.domain.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: bugProvider
 * @date: 2019/12/20 11:16
 * @description:
 */
@RestController
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("test")
    public User testInsert(){
        User user = new User();
        user.setWxId("111");
        user.setWxNickname("fff");
        user.setRoles("dddd");
        user.setAvatarUrl("222323");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setBonus(0);

        userMapper.insertSelective(user);
        return user;
    }
}
