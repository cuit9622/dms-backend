package cuit9622.dms.user.controller;

import cuit9622.dms.common.entity.User;
import cuit9622.dms.user.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserApiController {

    @Resource
    UserMapper userMapper;

    @GetMapping("/user")
    public User getUserByUserName(String username) {
        return userMapper.getUserByUsername(username);
    }
}
