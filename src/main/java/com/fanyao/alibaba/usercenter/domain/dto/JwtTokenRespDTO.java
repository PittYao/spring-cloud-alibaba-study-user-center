package com.fanyao.alibaba.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: bugProvider
 * @date: 2020/10/27 09:08
 * @description: jwt token
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenRespDTO {

    private String token;

    private Long expirationTime;
}
