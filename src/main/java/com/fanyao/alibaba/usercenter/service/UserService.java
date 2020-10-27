package com.fanyao.alibaba.usercenter.service;

import com.fanyao.alibaba.usercenter.dao.user.UserMapper;
import com.fanyao.alibaba.usercenter.domain.dto.UserLoginDTO;
import com.fanyao.alibaba.usercenter.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

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

    public User login(UserLoginDTO userLoginDTO, String openId) {
        User user = this.userMapper.selectOne(
                User.builder()
                        .wxId(openId)
                        .build()
        );

        if (Objects.isNull(user)) {
            // 入库
            User userToSave = User.builder()
                    .wxId(openId)
                    .bonus(200)
                    .wxNickname(userLoginDTO.getWxNickName())
                    .avatarUrl(userLoginDTO.getAvatarUrl())
                    .roles("user")
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            this.userMapper.insertSelective(
                    userToSave
            );

            return userToSave;
        }

        return user;
    }

}
