package com.fanyao.alibaba.usercenter.controller.user;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.fanyao.alibaba.usercenter.domain.dto.login.JwtTokenRespDTO;
import com.fanyao.alibaba.usercenter.domain.dto.login.LoginRespDTO;
import com.fanyao.alibaba.usercenter.domain.dto.login.UserLoginDTO;
import com.fanyao.alibaba.usercenter.domain.dto.login.UserRespDTO;
import com.fanyao.alibaba.usercenter.domain.entity.User;
import com.fanyao.alibaba.usercenter.service.UserService;
import com.fanyao.alibaba.usercenter.util.JwtOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: bugProvider
 * @date: 2019/12/20 14:09
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final WxMaService wxMaService;
    private final JwtOperator jwtOperator;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        log.info("接收GET请求");
        return userService.findById(id);
    }

    @GetMapping("/gen-token")
    public String genToken() {
        Map<String, Object> userInfo = new HashMap<>(3);
        userInfo.put("id", 22);
        userInfo.put("wxNickName", "测试");
        userInfo.put("role", "admin");

        return jwtOperator.generateToken(userInfo);
    }

    @GetMapping("/q")
    public User query(User user) {
        log.info("接收GET请求");
        return user;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        log.info("接收POST请求");
        return user;
    }

    /**
     *
     */
    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody UserLoginDTO userLoginDTO) throws WxErrorException {
        // 微信小程序服务端校验是否已经登录的结果
        WxMaJscode2SessionResult sessionInfo = this.wxMaService.getUserService()
                .getSessionInfo(userLoginDTO.getCode());

        // 微信的openId,用户在微信的唯一id；如果没有登录会报错
        String openid = sessionInfo.getOpenid();

        // 用户是否注册，未注册就入库
        // 已注册，颁发token
        User user = userService.login(userLoginDTO, openid);

        // 颁发token
        Map<String, Object> userInfo = new HashMap<>(3);
        userInfo.put("id", user.getId());
        userInfo.put("wxNickName", user.getWxNickname());
        userInfo.put("role", user.getRoles());

        String token = jwtOperator.generateToken(userInfo);

        log.info(
                "用户{}登录成功，生成token={}，有效期：{}",
                userLoginDTO.getWxNickName(),
                token,
                jwtOperator.getExpirationDateFromToken(token)
        );

        // 构建响应
        return LoginRespDTO.builder()
                .token(
                        JwtTokenRespDTO.builder()
                                .token(token)
                                .expirationTime(jwtOperator.getExpirationDateFromToken(token).getTime())
                                .build()
                )
                .user(
                        UserRespDTO.builder()
                                .id(user.getId())
                                .avatarUrl(user.getAvatarUrl())
                                .bonus(user.getBonus())
                                .wxNickName(user.getWxNickname())
                                .build()

                )
                .build();
    }

}
