package com.fanyao.alibaba.usercenter.domain.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: bugProvider
 * @date: 2020/10/27 09:09
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRespDTO {
    private Integer id;
    private String avatarUrl;
    /**
     * 积分
     */
    private Integer bonus;
    private String wxNickName;

}
