package com.fanyao.alibaba.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: bugProvider
 * @date: 2020/10/27 09:15
 * @description: 登录响应DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRespDTO {
    private JwtTokenRespDTO token;
    private UserRespDTO user;
}
