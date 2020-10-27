package com.fanyao.alibaba.usercenter.auth;

import com.fanyao.alibaba.usercenter.exception.CustomSecurityException;
import com.fanyao.alibaba.usercenter.util.JwtOperator;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @author: bugProvider
 * @date: 2020/10/27 14:35
 * @description: Aop 验证是否登录
 */
@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckLoginAspect {
    private final JwtOperator jwtOperator;

    @Around("@annotation(com.fanyao.alibaba.usercenter.auth.CheckLogin)")
    public Object checkLogin(ProceedingJoinPoint point) {
        try {
            // 1. header中是否有token
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            assert servletRequestAttributes != null;
            HttpServletRequest request = servletRequestAttributes.getRequest();

            String token = request.getHeader("Token");

            if (Strings.isBlank(token)) {
                // 抛异常
                throw new CustomSecurityException("请求头未携带Token");
            }

            // 2. 检验token是否合法，不合法抛异常
            Boolean validateToken = jwtOperator.validateToken(token);
            if (!validateToken) {
                throw new SecurityException("Token不合法");
            }

            // 3. 校验成功，将用户信息设置到request的attribute里
            Claims claims = jwtOperator.getClaimsFromToken(token);

            request.setAttribute("id", claims.get("id"));
            request.setAttribute("wxNickName", claims.get("wxNickName"));
            request.setAttribute("role", claims.get("role"));

            return point.proceed();
        } catch (Throwable throwable) {
            throw new CustomSecurityException("Token不合法");
        }
    }
}
