package com.fanyao.alibaba.usercenter.controller.user;

import com.fanyao.alibaba.usercenter.domain.entity.user.User;
import com.fanyao.alibaba.usercenter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        log.info("接收GET请求");
        return userService.findById(id);
    }

    // q=?id=xxx&wxid=xxx&...
    @GetMapping("/q")
    public User query(User user){
        return user;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        return user;
    }

}
