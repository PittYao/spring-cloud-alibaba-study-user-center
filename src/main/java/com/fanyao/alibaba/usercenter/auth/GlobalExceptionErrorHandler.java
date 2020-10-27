package com.fanyao.alibaba.usercenter.auth;

import com.fanyao.alibaba.usercenter.ErrorBody;
import com.fanyao.alibaba.usercenter.exception.CustomSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: bugProvider
 * @date: 2020/10/27 15:01
 * @description: 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionErrorHandler {

    @ExceptionHandler(CustomSecurityException.class)
    public ResponseEntity<ErrorBody> error(CustomSecurityException e) {
        log.warn("发生CustomSecurityException异常", e);

        return new ResponseEntity<>(
                ErrorBody.builder()
                        .body("Token非法,不能访问")
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }
}
