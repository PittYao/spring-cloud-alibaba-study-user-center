package com.fanyao.alibaba.usercenter.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;


/**
 * @author: bugProvider
 * @date: 2020/10/27 14:42
 * @description:
 */

@AllArgsConstructor
@Builder
public class CustomSecurityException extends RuntimeException {

    public CustomSecurityException(String message) {
        super(message);
    }
}
