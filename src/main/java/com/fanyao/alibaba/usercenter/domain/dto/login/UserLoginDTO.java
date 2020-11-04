package com.fanyao.alibaba.usercenter.domain.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: bugProvider
 * @date: 2020/10/27 09:17
 * @description: 登录DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDTO {
    private String code;
    /**
     * 头像地址
     */
    private String avatarUrl;
    /**
     * 微信昵称
     */
    private String wxNickName;

}
